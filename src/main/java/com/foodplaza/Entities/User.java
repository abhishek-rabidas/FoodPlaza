package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(updatable = false, unique = true)
    private String uid;
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