package com.lawagency.lawly.repository;

import com.lawagency.lawly.model.User;

public interface UserRepository extends EntityRepository<User>{
    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    User findByUsername(String username);
}
