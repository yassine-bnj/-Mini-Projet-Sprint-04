package com.example.livres.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.livres.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}