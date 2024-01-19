package com.example.VideoShareApp.services;

import com.example.VideoShareApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

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

//    public Optional<User> getUserByEmail(String email)  {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("email").is(email));
//
//        try {
//            return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
//        } catch (Exception e) {
//            // Handle potential exceptions (e.g., MongoException)
//            System.err.println("Error retrieving user: " + e.getMessage());
//            return Optional.empty();
//        }
//    }

}
