package com.foodplaza.DTO;

import lombok.Getter;

@Getter
public class RegisterUserRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
