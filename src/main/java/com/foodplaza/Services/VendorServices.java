package com.foodplaza.Services;

import com.foodplaza.Entities.Dishes;
import com.foodplaza.Entities.Restaurant;
import com.foodplaza.Entities.User;
import com.foodplaza.Repositories.DishesRepository;
import com.foodplaza.Repositories.RestaurantRepository;
import com.foodplaza.Repositories.UserRepository;
import com.foodplaza.Views.DishAddRequest;
import com.foodplaza.Views.RestaurantRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServices {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishesRepository dishesRepository;

    public void registerRestaurant(RestaurantRegistrationRequest request, String vendorName){
        User vendor = userRepository.findByUsername(vendorName);
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setVendor(vendor);
        restaurantRepository.save(restaurant);
    }

    public void addDish(DishAddRequest request, Long restaurantID){
        Restaurant restaurant = restaurantRepository.getOne(restaurantID);
        Dishes dish = new Dishes();
        dish.setName(request.getName());
        dish.setImage(request.getImage());
        dish.setDetails(request.getDetails());
        dish.setPrice(request.getPrice());
        dish.setRestaurant(restaurant);
        dishesRepository.save(dish);
    }
}
