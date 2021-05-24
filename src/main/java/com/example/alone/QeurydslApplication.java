package com.example.alone;

import com.example.alone.domain.userEntity.UserEntityMain;
import com.example.alone.domain.userEntity.UserEntityRepository;
import com.example.alone.domain.userEntity.UserRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class QeurydslApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QeurydslApplication.class, args);
        UserEntityRepository userEntityRepository = context.getBean(UserEntityRepository.class);

        userEntityRepository.save(new UserEntityMain("홍길동", 33, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("김동민", 12, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("이순신", 22, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("박나래", 32, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("철수홍", 44, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("영희", 55, UserRole.USER));
        userEntityRepository.save(new UserEntityMain("강진홍", 52, UserRole.ADMIN));
        userEntityRepository.save(new UserEntityMain("정재원", 42, UserRole.ADMIN));
        userEntityRepository.save(new UserEntityMain("김바다", 41, UserRole.ADMIN));
        userEntityRepository.save(new UserEntityMain("이수철", 66, UserRole.USER));
        
        List<UserEntityMain> resultList = userEntityRepository.findAllLike("%홍%");
        System.out.printf("이름에 홍을 포함한 인원 수:%d\n", resultList.size());

        for(UserEntityMain u : resultList){
            System.out.println(u.getUserName());
        }
    }
}
