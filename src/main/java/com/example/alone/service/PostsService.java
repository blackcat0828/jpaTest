package com.example.alone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.alone.domain.posts.Posts;
import com.example.alone.domain.posts.PostsRepository;
import com.example.alone.dto.PostsListResponseDto;
import com.example.alone.dto.PostsResponseDto;
import com.example.alone.dto.PostsSaveRequestDto;
import com.example.alone.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
        posts.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}
	
	public PostsResponseDto findById (Long id) {
		Posts entity = postsRepository.findById(id)
						.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		return new PostsResponseDto(entity);
	}
	
	
	//(readOnly = true)
	//트랜잭션 범위는 유지하되 조회 기능만 남겨두어 조회 속도가 개선됨
	//등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에 사용하는 것을 추천
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc(){
		return postsRepository.findAllDesc().stream()
					//밑에 코드를 람다식이 아닌 원래대로 풀면 다음과 같음
					//.map(posts -> new PostsListResponseADto(posts))
					.map(PostsListResponseDto::new)
					.collect(Collectors.toList());
	}
	
	@Transactional
	public void delete (Long id) {
		Posts posts = postsRepository.findById(id).orElseThrow(() -> new 
					IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		postsRepository.delete(posts);
	}
}
