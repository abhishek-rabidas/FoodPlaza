package com.foodplaza.Services;

import com.foodplaza.Config.CustomUserDetails;
import com.foodplaza.Entities.User;
import com.foodplaza.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user == null) throw new UsernameNotFoundException("User doesn't exists");
        else return new CustomUserDetails(user);
    }
}