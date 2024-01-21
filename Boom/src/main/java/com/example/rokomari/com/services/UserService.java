package com.example.rokomari.com.services;

import com.example.rokomari.com.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addUser(User user) {
        mongoTemplate.save(user);
    }
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }
}
