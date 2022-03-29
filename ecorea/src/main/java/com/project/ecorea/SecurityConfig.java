package com.project.ecorea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.ecorea.service.LoginFailureHandler;
import com.project.ecorea.service.LoginService;
import com.project.ecorea.service.LoginSuccessHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 private PasswordEncoder passwordEncoder;
	 private LoginService loginService;
	 private LoginSuccessHandler successHandler;
	 private LoginFailureHandler failureHandler;
	 
	 @Bean public DaoAuthenticationProvider daoAuthenticationProvider() {
	 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 provider.setPasswordEncoder(passwordEncoder);
	 provider.setUserDetailsService(loginService); return provider;
	 }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/general/login").loginProcessingUrl("/general/login")
		.usernameParameter("id").passwordParameter("pw")
		.successHandler(successHandler).failureHandler(failureHandler);
		
		http.logout().logoutUrl("/general/logout").logoutSuccessUrl("/general/login");
		
		http.csrf().disable();
	}
}
