package com.foodplaza.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class OrderResponse {
    private Long orderID;
    private String dishName;
    private String restaurantName;
    private Date date;

    public OrderResponse(Long orderID, String dishName, String restaurantName, Date date) {
        this.orderID = orderID;
        this.dishName = dishName;
        this.restaurantName = restaurantName;
        this.date = date;
    }
}
