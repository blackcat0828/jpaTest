package com.example.alone.domain.userEntity;


import com.querydsl.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryImpl(){
        super(UserEntityMain.class);
    }

    @Override
    @Autowired
    public void setEntityManager(EntityManager entityManager){
        super.setEntityManager(entityManager);
    }

    @Override
    public List findAllLike(String keyword){
        QUserEntityMain qUserEntityMain = QUserEntityMain.userEntityMain;
        JPQLQuery<UserEntityMain> query = from(qUserEntityMain);
        List<UserEntityMain> resultList = query.where(qUserEntityMain.userName.like(keyword)).fetch();

        return resultList;
    }
}
