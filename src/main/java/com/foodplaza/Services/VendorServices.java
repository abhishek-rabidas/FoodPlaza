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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class VendorServices {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishesRepository dishesRepository;

    public void registerRestaurant(RestaurantRegistrationRequest request, String base64auth){
        System.out.println("Adding Restaurant");
        String encodedString = base64auth.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User vendor = userRepository.findByUsername(values[0]);
        if(vendor == null) throw new UsernameNotFoundException("User doesn't exist");
        if(!vendor.getRole().equals("Vendor")) throw new RuntimeException("Your are not allowed to add restaurant");

        else{
                Restaurant restaurant = new Restaurant();
                restaurant.setName(request.getName());
                restaurant.setAddress(request.getAddress());
                restaurant.setVendor(vendor);
                restaurantRepository.save(restaurant);
        }
    }

    public void addDish(DishAddRequest request, Long restaurantID, String base64auth){
        System.out.println("Adding Dish");
        String encodedString = base64auth.split(" ")[1];
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        User vendor = userRepository.findByUsername(values[0]);
        if(vendor == null) throw new UsernameNotFoundException("User doesn't exist");
        Restaurant restaurant = restaurantRepository.getOne(restaurantID);
        if(restaurant == null) throw new RuntimeException("Restaurant doesn't exist");
        if(!vendor.getRole().equals("Vendor") && restaurant.getVendor()!=vendor) throw new RuntimeException(
                    "Your are not allowed to add dish");
        else{
                Dishes dish = new Dishes();
                dish.setName(request.getName());
                dish.setImage(request.getImage());
                dish.setDetails(request.getDetails());
                dish.setPrice(request.getPrice());
                dish.setRestaurant(restaurant);
                dishesRepository.save(dish);
            }
    }
}