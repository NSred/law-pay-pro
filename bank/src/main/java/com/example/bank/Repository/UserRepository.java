package com.example.bank.Repository;

import com.example.bank.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods if needed
}
