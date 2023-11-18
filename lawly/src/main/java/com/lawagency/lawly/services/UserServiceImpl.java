package com.lawagency.lawly.services;

import com.lawagency.lawly.model.User;
import com.lawagency.lawly.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public boolean existsByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

    public void save(User user) {
        repository.save(user);
    }
}
