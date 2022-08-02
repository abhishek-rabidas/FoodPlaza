package com.foodplaza.Controllers;

import com.foodplaza.Services.VendorServices;
import com.foodplaza.DTO.DishAddRequest;
import com.foodplaza.DTO.RestaurantRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    @Autowired
    private VendorServices vendorServices;

    @PostMapping("add-restaurant")
    public void addRestaurant(@RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest,
                              @RequestHeader("Authorization") String base64auth){
        vendorServices.registerRestaurant(restaurantRegistrationRequest, base64auth);
    }

    @PostMapping("{id}/add-dish")
    public void addDish(@RequestBody DishAddRequest dishAddRequest, @RequestHeader("Authorization") String base64auth,
                        @PathVariable("id") Long id){
        vendorServices.addDish(dishAddRequest, id, base64auth);
    }
}