package com.winterfell.lacus.freemarker.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.winterfell.lacus.entity.User;
import com.winterfell.lacus.entity.User.Status;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.utility.DeepUnwrap;

public class EnumInterpreter implements TemplateDirectiveModel {

	private final static String DIRECTIVE_ENUM_KEY = "enum";

	private static Map<Object, String> enumTranslate = new HashMap<>();
	static {
		enumTranslate.put(Status.NORMAL, "正常");
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		// ContextLoader.getCurrentWebApplicationContext().getBean(arg0)
		((User) ((StringModel) params.get("name")).getWrappedObject()).getId();
		Object status = ((StringModel) params.get(DIRECTIVE_ENUM_KEY)).getWrappedObject();
		env.getOut().write(enumTranslate.get(status));
	}
}
