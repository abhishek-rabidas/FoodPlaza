package com.foodplaza.Services;

import com.foodplaza.DTO.Response.UserResponse;
import com.foodplaza.Entities.User;
import com.foodplaza.DAO.UserRepository;
import com.foodplaza.DTO.LoginUserRequest;
import com.foodplaza.DTO.RegisterUserRequest;
import com.foodplaza.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse registerUser(RegisterUserRequest request, String role) {
        if (userRepository.countByUsername(request.getUsername()) > 0) {
            throw new RuntimeException("Username already exists");
        } else if (userRepository.countByEmail(request.getEmail()) > 0) {
            throw new RuntimeException("Email already exists");
        } else {
            User newUser = new User();
            newUser.setUid(UUID.randomUUID().toString());
            newUser.setJoiningDate(new Date());
            newUser.setName(request.getName());
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setRole(role);
            return new UserResponse(userRepository.save(newUser));
        }
    }

    public UserResponse loginUser(LoginUserRequest request) throws UserNotFoundException {
        if (passwordEncoder.matches(request.getPassword(),
                userRepository.findByUsername(request.getUsername()).getPassword())) {
            return new UserResponse(userRepository.findByUsername(request.getUsername()));
        } else {
            throw new UserNotFoundException("Invalid User");
        }
    }
}