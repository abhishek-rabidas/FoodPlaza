package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Dishes extends AbstractPersistable<Long>{
    private String name;
    private Double price;
    private String image;
    private String details;
    @ManyToOne
    private Restaurant restaurant;
}
