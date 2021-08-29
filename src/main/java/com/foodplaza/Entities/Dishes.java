package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Dishes {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double price;
    private String image;
    private String details;
    @ManyToOne
    private Restaurant restaurant;
}
