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

import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    public void placeOrder(OrderRequest orderRequest, String base64auth){
        String encodedString = base64auth.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User customer = userRepository.findByUsername(values[0]);
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