package com.foodplaza.Repositories;

import com.foodplaza.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
    User findByUsername(String username);
    Integer countByUsername(String username);
    Integer countByEmail(String email);
    List<User> findAllByRole(String role);
}