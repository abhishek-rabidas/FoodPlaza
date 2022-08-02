package com.foodplaza.Controllers;

import com.foodplaza.Services.UserServices;
import com.foodplaza.DTO.OrderRequest;
import com.foodplaza.DTO.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("order")
    public void placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String base64auth){
        userServices.placeOrder(orderRequest, base64auth);
    }

    @GetMapping("allorders")
    public List<OrderResponse> getAllOrders(@RequestHeader("Authorization") String base64auth){
        return userServices.getAllUserOrders(base64auth);
    }
}
