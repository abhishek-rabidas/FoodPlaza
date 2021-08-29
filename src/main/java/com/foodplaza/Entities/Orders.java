package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Dishes dish;
    @OneToOne
    private Restaurant restaurant;
    private Date orderTime;
    private Double orderPrice;
}
