package com.foodplaza.Controllers;

import com.foodplaza.DTO.Common.CommonApiResponse;
import com.foodplaza.DTO.Response.UserResponse;
import com.foodplaza.Entities.Orders;
import com.foodplaza.Services.UserServices;
import com.foodplaza.DTO.OrderRequest;
import com.foodplaza.DTO.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("order")
    public ResponseEntity<CommonApiResponse<Orders>> placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String base64auth){
        CommonApiResponse<Orders> response = new CommonApiResponse<>();
        try {
            response.setData(userServices.placeOrder(orderRequest, base64auth));
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

    @GetMapping("allorders")
    public ResponseEntity<CommonApiResponse<List<OrderResponse>>> getAllOrders(@RequestHeader("Authorization") String base64auth){
        CommonApiResponse<List<OrderResponse>> response = new CommonApiResponse<>();
        try {
            response.setData(userServices.getAllUserOrders(base64auth));
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
