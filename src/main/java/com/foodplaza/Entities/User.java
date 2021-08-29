package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private Date joiningDate;
}
