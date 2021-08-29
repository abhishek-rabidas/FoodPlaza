package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private User vendor;
}
