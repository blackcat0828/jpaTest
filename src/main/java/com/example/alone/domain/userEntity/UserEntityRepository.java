package com.example.alone.domain.userEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntityMain, Long>, UserRepositoryCustom {
    //UserEntityMain 클래스에서 사용자명 필드를 userName으로 선언했으므로
    //메서드에서도 똑같이 findByUserName으로 맞춘다.
    UserEntityMain findByUserName(@Param("userName") String userName);

}
