package com.foodplaza.DTO;

import lombok.Getter;

@Getter
public class DishAddRequest {
    private String name;
    private Double price;
    private String image;
    private String details;
}
