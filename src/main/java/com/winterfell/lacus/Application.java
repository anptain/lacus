package com.winterfell.lacus;

import java.util.List;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.winterfell.lacus.entity.User;
import com.winterfell.lacus.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		// 内存数据库操作
		userRepository.save(new User());

		List<User> users = userRepository.findAll();
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
//
//	@Bean
//	public FilterRegistrationBean registration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean(new ConfigurableSiteMeshFilter());
//	    registration.setFilter(new MySiteMeshFilter());
//	    return registration;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DefaultKaptcha defaultKaptcha() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties prop = new Properties();
//		prop.put("kaptcha.border", "no");
//		prop.put("kaptcha.border", "no");
//		prop.put("kaptcha.border.color", "105,179,90");
//		prop.put("kaptcha.textproducer.font.color", "red");
//		prop.put("kaptcha.image.width", "250");
//		prop.put("kaptcha.textproducer.font.size", "80");
//		prop.put("kaptcha.image.height", "90");
//		prop.put("kaptcha.session.key", "code");
//		prop.put("kaptcha.textproducer.char.length", "4");
//		prop.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		defaultKaptcha.setConfig(new Config(prop));
		return defaultKaptcha;
	}
}
