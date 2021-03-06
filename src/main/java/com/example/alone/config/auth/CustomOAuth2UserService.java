package com.example.alone.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.alone.config.auth.dto.OAuthAttributes;
import com.example.alone.config.auth.dto.SessionUser;
import com.example.alone.domain.user.User;
import com.example.alone.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//소셜 로그인 이후 가져온 사용자 정보(email, name, picture 등) 들을 기반으로 가입 및 정보수정, 세션 저장 등의 기능을 지원
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{
		private final UserRepository userRepository;
		private final HttpSession httpSession;
		
		@Override
		public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
			OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
						new DefaultOAuth2UserService();
			OAuth2User oAuth2User = delegate.loadUser(userRequest);
			
			//현재 로그인 진행 중인 서비스를 구분하는 코드
			//구글만 사용한다면 불필요하지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분
			String registrationId = userRequest.getClientRegistration().getRegistrationId();
			
			//OAuth2 로그인 진행 시 키가 되는 필드값을 이야기한다. Primary Key와 같은 의미이다.
			//구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않는다. 구글의 기본코드는 "sub"이다
			//이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용된다.
			String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
									.getUserInfoEndpoint().getUserNameAttributeName();
			
			
			//OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스이다.
			//이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용한다.
			OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, 
													oAuth2User.getAttributes());
			
			User user = saveOrUpdate(attributes);
	        httpSession.setAttribute("user", 
	        				new SessionUser(user));
	        				//SessionUser
	        				//세션에 사용자 정보를 저장하기 위한 Dto 클래쓰이다.
	        				//왜 User 클래스를 쓰지 않고 새로 만들어서 쓰는지는 아래에 설명
	        				//

	        return new DefaultOAuth2User(
	                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
	                attributes.getAttributes(),
	                attributes.getNameAttributeKey());
		}
		
		private User saveOrUpdate(OAuthAttributes attributes) {
			User user = userRepository.findByEmail(attributes.getEmail())
	                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
	                .orElse(attributes.toEntity());
			
	        return userRepository.save(user);
		}
	
}
