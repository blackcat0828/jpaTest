package com.example.alone.config.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.alone.domain.user.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final CustomOAuth2UserService customOAuth2UserService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable().headers().frameOptions().disable() //h2-console 화면 사용하기 위해 해당 옵션들을 diable
		.and()
			.authorizeRequests() //URL별 권한 관리를 설정하는 옵션의 시작점 authorizeRequests가 선언되어야만 antMatchers사용 가능
			//antMatchers - URL, HTTP 메소드별 관리가 가능하다.
			.antMatchers("/", "/css/**", "/images/**", "js/**", "/h2-console/**").permitAll()
			.antMatchers("/api/v1/**").hasRole("USER")
			.anyRequest().authenticated() //anyRequest 설정된 값들 외에 나머지 URL들을 나타낸다.
		.and()
			.logout()
				.logoutSuccessUrl("/")
		.and()
			.oauth2Login() //Oauth2 로그인 기능에 대한 여러 설정의 진입점
				.userInfoEndpoint() //Oauth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
					.userService(customOAuth2UserService);
					//userService - 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
					//리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시 가능
		
		
	}
}
