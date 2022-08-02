package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Orders extends AbstractPersistable<Long>{
    @OneToOne
    private User user;
    private String username;
    private String name;
    private String email;
    @OneToOne
    private Dishes dish;
    private String dishName;
    @OneToOne
    private Restaurant restaurant;
    private String restaurantName;
    private Date orderTime;
    private Double orderPrice;
}
