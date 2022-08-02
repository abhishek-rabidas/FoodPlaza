package com.foodplaza.DTO.Response;

import com.foodplaza.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String uid;
    private String name;
    private String email;
    private String username;
    private String role;
    private String joiningDate;

    public UserResponse(User user) {
        this.uid = user.getUid();
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.joiningDate = user.getJoiningDate().toString();
    }
}