package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class User {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String role;
    private Date joiningDate;
}
