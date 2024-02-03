package com.nhat.project.repository;

import com.nhat.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
