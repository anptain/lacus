package com.winterfell.lacus.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winterfell.lacus.common.UserStatus;
import com.winterfell.lacus.entity.User;
import com.winterfell.lacus.exception.BadCaptchaException;
import com.winterfell.lacus.web.form.RegisterForm;

@Controller
public class HomeController extends AbstractController {
	@RequestMapping("/")
	public String home(Model model,HttpServletRequest request) {
		getUser();
		model.addAttribute("msg", "你好，世界");
		model.addAttribute("enum", UserStatus.NORMAL);
		model.addAttribute("test2", new User());
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, Model model, HttpSession session) {
		Object expcetion = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		if (error != null && expcetion != null) {
			if (expcetion instanceof BadCredentialsException) {
				model.addAttribute("loginMsg", "用户名或密码错误");
			}
			if (expcetion instanceof BadCaptchaException) {
				model.addAttribute("loginMsg", "验证码输入错误");
			}
		}
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@Valid RegisterForm register, BindingResult result) {
		if (result.hasErrors()) {
		}
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	// @Secured("ROLE_ADMIN")
	public String admin() {
		return "admin";
	}
}
