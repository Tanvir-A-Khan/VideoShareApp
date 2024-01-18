package com.example.rokomari.com.api.controller;

import com.example.rokomari.com.api.models.User;
import com.example.rokomari.com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8081/home/user
    @GetMapping("/user")
    public List<User> getUser(){
        System.out.println("getting user");
        return this.userService.getUsers();
    }

    @GetMapping("/currentuser")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

}
