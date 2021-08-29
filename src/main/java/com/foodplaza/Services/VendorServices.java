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
        try{
            if(!vendor.getRole().equals("Vendor")) throw new IllegalAccessException("Your are not allowed to add " +
                    "restaurant");
            else{
                Restaurant restaurant = new Restaurant();
                restaurant.setName(request.getName());
                restaurant.setAddress(request.getAddress());
                restaurant.setVendor(vendor);
                restaurantRepository.save(restaurant);
            }
        }catch (IllegalAccessException e){
            return;
        }
    }

    public void addDish(DishAddRequest request, Long restaurantID, String vendorName){
        User vendor = userRepository.findByUsername(vendorName);
        Restaurant restaurant = restaurantRepository.getOne(restaurantID);
        try{
            if(!vendor.getRole().equals("Vendor") && restaurant.getVendor()!=vendor) throw new IllegalAccessException(
                    "Your are not " +
                    "allowed to " +
                    "add " +
                    "dish");
            else{
                Dishes dish = new Dishes();
                dish.setName(request.getName());
                dish.setImage(request.getImage());
                dish.setDetails(request.getDetails());
                dish.setPrice(request.getPrice());
                dish.setRestaurant(restaurant);
                dishesRepository.save(dish);
            }
        }catch (IllegalAccessException e){
            return;
        }
    }
}