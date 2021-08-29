package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
    @Column(length = 100)
    private String email;
    @Column(length = 50)
    private String username;
    @Column(length = 60)
    private String password;
    @Column(length = 25)
    private String role;
    private Date joiningDate;
}