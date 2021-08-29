package com.foodplaza.Views;

import lombok.Getter;

import java.util.Date;

@Getter
public class RegisterUserRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
