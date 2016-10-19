package com.winterfell.lacus.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.winterfell.lacus.exception.BadCaptchaException;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model) {
		// UserDetails userDetails = (UserDetails)
		// SecurityContextHolder.getContext()
		// .getAuthentication()
		// .getPrincipal();
		model.addAttribute("msg", "你好，世界");
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, Model model, HttpSession session) {
		Object expcetion = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
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

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	//@Secured("ROLE_ADMIN")
	public String admin() {
		return "admin";
	}
}
