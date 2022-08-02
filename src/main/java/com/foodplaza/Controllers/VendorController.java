package com.foodplaza.Controllers;

import com.foodplaza.DTO.Common.CommonApiResponse;
import com.foodplaza.DTO.Response.UserResponse;
import com.foodplaza.Entities.Dishes;
import com.foodplaza.Entities.Restaurant;
import com.foodplaza.Services.VendorServices;
import com.foodplaza.DTO.DishAddRequest;
import com.foodplaza.DTO.RestaurantRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("api/v1/vendor")
public class VendorController {

    @Autowired
    private VendorServices vendorServices;

    @PostMapping("add-restaurant")
    public ResponseEntity<CommonApiResponse<Restaurant>> addRestaurant(@RequestBody RestaurantRegistrationRequest restaurantRegistrationRequest,
                                                                       @RequestHeader("Authorization") String base64auth) {
        CommonApiResponse<Restaurant> response = new CommonApiResponse<>();
        try {
            response.setData(vendorServices.registerRestaurant(restaurantRegistrationRequest, base64auth));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("{id}/add-dish")
    public ResponseEntity<CommonApiResponse<Dishes>> addDish(@RequestBody DishAddRequest dishAddRequest, @RequestHeader("Authorization") String base64auth,
                                                             @PathVariable("id") Long id) {
        CommonApiResponse<Dishes> response = new CommonApiResponse<>();
        try {
            response.setData(vendorServices.addDish(dishAddRequest, id, base64auth));
            return ResponseEntity.ok(response);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            response.setStatusCode(e.getStatusCode().value());
            response.setMessage(e.getStatusText());
            return ResponseEntity.status(e.getStatusCode()).body(response);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}