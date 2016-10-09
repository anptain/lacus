package com.winterfell.lacus.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.winterfell.lacus.entity.Authorities;
import com.winterfell.lacus.entity.User;
import com.winterfell.lacus.repository.AuthoritiesRepository;
import com.winterfell.lacus.repository.UserRepository;

@Service
public class DataInit {
	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private AuthoritiesRepository authoritiesRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @PostConstruct
    public void dataInit(){
        User admin = new User();
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setUsername("admin");
        userRepository.save(admin);
        Authorities adminAuthorities = new Authorities();
        adminAuthorities.setUser(admin);
        adminAuthorities.setRole("ROLE_ADMIN");
        authoritiesRepository.save(adminAuthorities);

        User user = new User();
        user.setPassword(passwordEncoder.encode("user"));
        user.setUsername("user");
        userRepository.save(user);
        Authorities userAuthorities = new Authorities();
        adminAuthorities.setUser(user);
        adminAuthorities.setRole("ROLE_USER");
        authoritiesRepository.save(userAuthorities);
    }
}
