package com.example.VideoShareApp.controller;

import com.example.VideoShareApp.model.User;
import com.example.VideoShareApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:5173/")
public class userController {
    @Autowired
    private UserService userService;

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

    @GetMapping("/get-by-email")
    public User addUser(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/login")
    public ResponseEntity<User> findUserByEmailAndPassword(
            @RequestParam String email,
            @RequestParam String password) {

        Optional<User> userOptional = userService.findUserByEmailAndPassword(email, password);

        return userOptional
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
