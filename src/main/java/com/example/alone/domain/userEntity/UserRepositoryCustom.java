package com.example.alone.domain.userEntity;

import java.util.List;

public interface UserRepositoryCustom {
    List findAllLike(String keyword);
}
