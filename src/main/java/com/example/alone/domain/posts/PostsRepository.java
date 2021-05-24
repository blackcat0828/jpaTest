package com.example.alone.domain.posts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//JpaRepository<Entity 클래스, PK 타입>
//주의할 점
//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다. 
public interface PostsRepository extends JpaRepository<Posts, Long> {
	
	//네이티브 쿼리 사용
	@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
	List<Posts> findAllDesc();
	

}


//자바 제네릭에서 Type 표기 약어의 의미
// E - Element (요소를 의미하는 약어)
// K - Key(키를 의미하는 약어)
// N - Number(숫자를 의미하는 약어)
// T - Type (타입을 의마하는 약어)
// V - Value (값을 의미하는 약어)