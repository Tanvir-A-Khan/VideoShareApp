package com.example.rokomari.com.api.controller;

import com.example.rokomari.com.api.models.User;
import com.example.rokomari.com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/user")
    public List<User> addUser(@RequestBody User user) {
        System.out.println("Adding user: " + user);
        userService.addUser(user);
        return userService.getUsers();
    }

    @GetMapping("/currentuser")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }

}
