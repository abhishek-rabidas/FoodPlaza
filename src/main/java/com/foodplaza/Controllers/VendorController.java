package com.foodplaza.Controllers;

import com.foodplaza.Services.VendorServices;
import com.foodplaza.Views.RestaurantRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    @Autowired
    private VendorServices vendorServices;

    @PostMapping("addrestaurant")
    public void addRestaurant(@RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest,
                              @RequestHeader("username") String username){
        vendorServices.registerRestaurant(restaurantRegistrationRequest, username);
    }
}