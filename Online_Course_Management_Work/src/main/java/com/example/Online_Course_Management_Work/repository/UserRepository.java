package com.example.Online_Course_Management_Work.repository;

import com.example.Online_Course_Management_Work.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

