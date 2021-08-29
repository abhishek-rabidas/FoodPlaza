package com.foodplaza.Services;

import com.foodplaza.Entities.Dishes;
import com.foodplaza.Entities.Orders;
import com.foodplaza.Entities.Restaurant;
import com.foodplaza.Entities.User;
import com.foodplaza.Repositories.DishesRepository;
import com.foodplaza.Repositories.OrdersRepository;
import com.foodplaza.Repositories.RestaurantRepository;
import com.foodplaza.Repositories.UserRepository;
import com.foodplaza.Views.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServices {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public void placeOrder(OrderRequest orderRequest, String username){
        User customer = userRepository.findByUsername(username);
        Dishes orderedDish = dishesRepository.getOne(orderRequest.getDish());
        Restaurant orderedRestaurant = restaurantRepository.getOne(orderRequest.getRestaurant());
        Orders order = new Orders();
        order.setUser(customer);
        order.setDish(orderedDish);
        order.setRestaurant(orderedRestaurant);
        order.setOrderTime(new Date());
        order.setOrderPrice(orderedDish.getPrice());
        ordersRepository.save(order);
    }
}