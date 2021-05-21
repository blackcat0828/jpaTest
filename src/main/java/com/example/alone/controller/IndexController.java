package com.example.alone.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.alone.config.auth.LoginUser;
import com.example.alone.config.auth.dto.SessionUser;
import com.example.alone.dto.PostsResponseDto;
import com.example.alone.service.PostsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	
	private final PostsService postsService;
	private final HttpSession httpSession;
	
	
	//SessionUser를 httpSession으로 불러오는 코드를 제거하기 위해 커스텀으로 만든 @LoginUser 적용 
	//어느 컨트롤러던지 @LoginUser만 붙이면 세션 정보를 가져올 수 있다.
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		
		model.addAttribute("posts", postsService.findAllDesc());
		if(user != null) {
			model.addAttribute("userName2", user.getName());
		}
		
		return "index";
	}
	
	/*
	 * @GetMapping("/") public String index(Model model) {
	 * 
	 * model.addAttribute("posts", postsService.findAllDesc());
	 * 
	 * //앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했었다. //즉
	 * 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다. SessionUser user =
	 * (SessionUser) httpSession.getAttribute("user");
	 * 
	 * //세션에 저장된 값이 있을 때만 model에 userName으로 등록한다. //세션에 저장된 값이 없으면 model엔 아무런 값이 없는
	 * 상태이니 로그인 버튼이 보이게 된다. if(user != null) {
	 * 
	 * model.addAttribute("userName2", user.getName());
	 * 
	 * }
	 * 
	 * 
	 * return "index"; }
	 */
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("post", dto);
		return "posts-update";
	}
}
