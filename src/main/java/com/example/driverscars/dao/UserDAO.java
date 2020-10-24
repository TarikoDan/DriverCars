package com.example.driverscars.dao;

import com.example.driverscars.entiti.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    void removeUserByEmail(String email);
    User getUserByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
