package com.example.alone.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.alone.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final CustomOAuth2UserService customOAuth2UserService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.headers().frameOptions().disable()
		.and()
			.authorizeRequests()
			.antMatchers("/", "/css/**", "/images/**", "js/**", "/h2-console/**").permitAll()
			.antMatchers("/api/v1/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
		.and()
			.logout()
				.logoutSuccessUrl("/")
		.and()
			.oauth2Login()
				.userInfoEndpoint()
					.userService(customOAuth2UserService);
		
		
	}
}
