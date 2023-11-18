package com.lawagency.lawly.services;

import com.lawagency.lawly.model.User;

public interface UserService {
    public boolean existsByUsername(String username) ;
    public boolean existsByEmail(String email) ;
    public void save(User user) ;
}
