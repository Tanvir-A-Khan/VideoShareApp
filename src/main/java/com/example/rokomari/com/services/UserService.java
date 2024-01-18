package com.example.rokomari.com.services;

import com.example.rokomari.com.api.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private List<User> store = new ArrayList<>();
    public UserService(){
        store.add(new User(UUID.randomUUID().toString(), "Tanvir0","Tanvir0@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Tanvi1","Tanvir1@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Tanvir2","Tanvir2@gmail.com"));
        store.add(new User(UUID.randomUUID().toString(), "Tanvir3","Tanvir3@gmail.com"));
    }

    public List<User> getUsers(){
        return this.store;
    }
}
