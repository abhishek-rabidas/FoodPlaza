package com.foodplaza.Controllers;

import com.foodplaza.DTO.Common.CommonApiResponse;
import com.foodplaza.DTO.Response.UserResponse;
import com.foodplaza.Entities.User;
import com.foodplaza.Services.AuthenticationServices;
import com.foodplaza.DTO.LoginUserRequest;
import com.foodplaza.DTO.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationServices authServices;

    @PostMapping("register/customer")
    public ResponseEntity<CommonApiResponse<UserResponse>> registerCustomer(@RequestBody RegisterUserRequest request) {
        CommonApiResponse<UserResponse> response = new CommonApiResponse<>();
        try {
            response.setData(authServices.registerUser(request, "Customer"));
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

    @PostMapping("register/vendor")
    public ResponseEntity<CommonApiResponse<UserResponse>> registerVendor(@RequestBody RegisterUserRequest request) {
        CommonApiResponse<UserResponse> response = new CommonApiResponse<>();
        try {
            response.setData(authServices.registerUser(request, "Vendor"));
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

    @PostMapping("login")
    public ResponseEntity<CommonApiResponse<UserResponse>> loginUser(@RequestBody LoginUserRequest request) {
        CommonApiResponse<UserResponse> response = new CommonApiResponse<>();
        try {
            response.setData(authServices.loginUser(request));
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
