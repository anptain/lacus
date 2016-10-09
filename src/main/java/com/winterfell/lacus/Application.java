package com.winterfell.lacus;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
