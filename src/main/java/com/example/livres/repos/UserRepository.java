package com.example.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
