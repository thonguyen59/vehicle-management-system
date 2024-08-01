package com.assignment.vehiclemanagementsystem.repository;

import java.util.Optional;

import com.assignment.vehiclemanagementsystem.entity.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
