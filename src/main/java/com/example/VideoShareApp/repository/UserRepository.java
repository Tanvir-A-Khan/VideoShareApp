package com.example.VideoShareApp.repository;

import com.example.VideoShareApp.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {

    //JPA Query -> Converted to mongo directly
    List<User> findByEmail(String email);
    //Hibernet
    Optional<User> findByEmailAndPassword(String email, String password);
}
