package com.example.VideoShareApp.services;

import com.example.VideoShareApp.model.User;
import com.example.VideoShareApp.model.Video;
import com.example.VideoShareApp.repository.UserRepository;
import com.example.VideoShareApp.repository.VideoRepository;
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

    private final UserRepository userRepository;

    public UserService(MongoTemplate mongoTemplate, UserRepository userRepository) {
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
    }

    public  User findByEmail(String email){
        return  userRepository.findByEmail(email).stream().findFirst().orElseGet(null);
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void addUser(User user) {
        mongoTemplate.save(user);
    }
    public List<User> getUsers() {
        return mongoTemplate.findAll(User.class);
    }



}
