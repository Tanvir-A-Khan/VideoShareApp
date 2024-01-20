package com.example.VideoShareApp.repository;

import com.example.VideoShareApp.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoRepository extends MongoRepository<Video, ObjectId> {
//    Optional<Video> findById(String ObjectId);
}
