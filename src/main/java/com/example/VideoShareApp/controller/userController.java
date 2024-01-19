package com.example.VideoShareApp.controller;

import com.example.VideoShareApp.model.User;
import com.example.VideoShareApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    //http://localhost:8081/home/user
    @GetMapping("/get")
    public List<User> getUser(){
        System.out.println("getting user");
        return this.userService.getUsers();
    }
    @PostMapping("/post")
    public List<User> addUser(@RequestBody User user) {
        System.out.println("Adding user: " + user);
        userService.addUser(user);
        return userService.getUsers();
    }

    @GetMapping("/getuserentity")
    public ResponseEntity<List<User>> getUserz(){
        System.out.println("getting user");
                this.userService.getUsers();
        return new ResponseEntity<>(this.userService.getUsers(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getSingleUser(@PathVariable String email){
//        return
//    }

}
