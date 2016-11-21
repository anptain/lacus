package com.winterfell.lacus.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParseException;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class AuthorizeDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Object access = params.get("access");
		if (authorizeUsingAccessExpression(access.toString())) {
			body.render(env.getOut());
		}
	}

	public boolean authorizeUsingAccessExpression(String access) throws IOException {
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return false;
		}

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		ServletRequest request = attr.getRequest();
		ServletResponse response = attr.getResponse();

		SecurityExpressionHandler<FilterInvocation> handler = getExpressionHandler(request.getServletContext());

		Expression accessExpression;
		try {
			accessExpression = handler.getExpressionParser().parseExpression(access);

		} catch (ParseException e) {
			IOException ioException = new IOException();
			ioException.initCause(e);
			throw ioException;
		}

		return ExpressionUtils.evaluateAsBoolean(accessExpression,
				createExpressionEvaluationContext(request, response, handler));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SecurityExpressionHandler<FilterInvocation> getExpressionHandler(ServletContext servletContext)
			throws IOException {
		ApplicationContext appContext = SecurityWebApplicationContextUtils
				.findRequiredWebApplicationContext(servletContext);
		Map<String, SecurityExpressionHandler> handlers = appContext.getBeansOfType(SecurityExpressionHandler.class);

		for (SecurityExpressionHandler h : handlers.values()) {
			if (FilterInvocation.class
					.equals(GenericTypeResolver.resolveTypeArgument(h.getClass(), SecurityExpressionHandler.class))) {
				return h;
			}
		}

		throw new IOException("No visible WebSecurityExpressionHandler instance could be found in the application "
				+ "context. There must be at least one in order to support expressions in JSP 'authorize' tags.");
	}

	/**
	 * Allows the {@code EvaluationContext} to be customized for variable lookup
	 * etc.
	 */
	protected EvaluationContext createExpressionEvaluationContext(ServletRequest request, ServletResponse response,
			SecurityExpressionHandler<FilterInvocation> handler) {
		FilterInvocation f = new FilterInvocation(request, response, new FilterChain() {
			public void doFilter(ServletRequest request, ServletResponse response)
					throws IOException, ServletException {
				throw new UnsupportedOperationException();
			}
		});

		return handler.createEvaluationContext(SecurityContextHolder.getContext().getAuthentication(), f);
	}
}
