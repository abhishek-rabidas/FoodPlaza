package com.foodplaza.Services;

import com.foodplaza.Entities.User;
import com.foodplaza.Repositories.UserRepository;
import com.foodplaza.Views.LoginUserRequest;
import com.foodplaza.Views.RegisterUserRequest;
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

    public boolean registerUser(RegisterUserRequest request, String role){
            if(userRepository.countByUsername(request.getUsername())>0){
                throw new RuntimeException("Username already exists");
            }else if(userRepository.countByEmail(request.getEmail())>0){
                throw new RuntimeException("Email already exists");
            }else{
                User newUser = new User();
                newUser.setUid(UUID.randomUUID().toString());
                newUser.setJoiningDate(new Date());
                newUser.setName(request.getName());
                newUser.setUsername(request.getUsername());
                newUser.setEmail(request.getEmail());
                newUser.setPassword(passwordEncoder.encode(request.getPassword()));
                newUser.setRole(role);
                userRepository.save(newUser);
                return true;
            }
    }

    public boolean loginUser(LoginUserRequest request){
        try{
            return passwordEncoder.matches(request.getPassword(),
                    userRepository.findByUsername(request.getUsername()).getPassword());
        }catch (NullPointerException e){
            throw new NullPointerException("User doesn't exist, please sign up");
        }
    }
}