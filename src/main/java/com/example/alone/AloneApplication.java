package com.example.alone;

import com.example.alone.domain.userEntity.School;
import com.example.alone.domain.userEntity.UserEntityMain;
import com.example.alone.domain.userEntity.UserEntityRepository;
import com.example.alone.domain.userEntity.UserRole;
import com.example.alone.service.SchoolService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class AloneApplication {

	public static void main(String[] args) {

		//스프링 부트 애플리케이션의 run 메서드는 ConfigurableApplicationContext를 반환한다.
		//그래서 run 메서드의 반환값으로 context를 얻어서 UserRepository 빈을 얻은 후 save 메서드로 데이터를 저장한다.
		ConfigurableApplicationContext context = SpringApplication.run(AloneApplication.class, args);
		SchoolService schoolService = context.getBean(SchoolService.class);

		//학교 기준으로 조회
		schoolService.findSchoolInfo();

		//학생 기준으로 조회
//		schoolService.findStudentInfo();


//		UserEntityRepository userEntityRepository = context.getBean(UserEntityRepository.class);
//		userEntityRepository.save(new UserEntityMain("윤사장", 60, UserRole.ADMIN));
//		UserEntityMain user = userEntityRepository.findByUserName("윤사장");
//		System.out.printf("나이: "+ user.getAge() + "," + "이름 : " + user.getUserName() + "," + "생성일 : " + user.getCreatedAt());

	}

}
