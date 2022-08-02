package com.foodplaza.Controllers;

import com.foodplaza.Services.AuthenticationServices;
import com.foodplaza.DTO.LoginUserRequest;
import com.foodplaza.DTO.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationServices authServices;

    @PostMapping("register/customer")
    public boolean registerCustomer(@RequestBody RegisterUserRequest request){
        return authServices.registerUser(request, "Customer");
    }

    @PostMapping("register/vendor")
    public boolean registerVendor(@RequestBody RegisterUserRequest request){
        return authServices.registerUser(request, "Vendor");
    }

    @PostMapping("login")
    public boolean loginUser(@RequestBody LoginUserRequest request){
        return authServices.loginUser(request);
    }
}
