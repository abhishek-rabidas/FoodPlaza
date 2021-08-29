package com.foodplaza.Repositories;

import com.foodplaza.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Integer countByUsername(String username);
    Integer countByEmail(String email);
    List<User> findAllByRole(String role);
}