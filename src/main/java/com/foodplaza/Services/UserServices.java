package com.foodplaza.Services;

import com.foodplaza.Entities.Dishes;
import com.foodplaza.Entities.Orders;
import com.foodplaza.Entities.Restaurant;
import com.foodplaza.Entities.User;
import com.foodplaza.DAO.DishesRepository;
import com.foodplaza.DAO.OrdersRepository;
import com.foodplaza.DAO.RestaurantRepository;
import com.foodplaza.DAO.UserRepository;
import com.foodplaza.DTO.OrderRequest;
import com.foodplaza.DTO.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public Orders placeOrder(OrderRequest orderRequest, String base64auth){
        String encodedString = base64auth.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User customer = userRepository.findByUsername(values[0]);
        Dishes orderedDish = dishesRepository.getOne(orderRequest.getDish());
        if(orderedDish == null)
            throw new RuntimeException("Dish not found!");
        Restaurant orderedRestaurant = restaurantRepository.getOne(orderRequest.getRestaurant());
        Orders order = new Orders();
        order.setUser(customer);
        order.setUsername(customer.getUsername());
        order.setName(customer.getName());
        order.setEmail(customer.getEmail());
        order.setDish(orderedDish);
        order.setDishName(orderedDish.getName());
        order.setRestaurant(orderedRestaurant);
        order.setRestaurantName(orderedRestaurant.getName());
        order.setOrderTime(new Date());
        order.setOrderPrice(orderedDish.getPrice());
        return ordersRepository.save(order);
    }

    public List<OrderResponse> getAllUserOrders(String base64auth){
        String encodedString = base64auth.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User customer = userRepository.findByUsername(values[0]);
        List<OrderResponse> orders= new ArrayList<>();
        ordersRepository.findAllByUser(customer.getId()).stream().filter(order -> {
            return orders.add(new OrderResponse(order.getId(), order.getDishName(), order.getRestaurantName(), order.getOrderTime()));
        }).collect(Collectors.toList());
        return orders;
    }
}