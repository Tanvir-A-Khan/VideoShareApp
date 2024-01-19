package com.example.VideoShareApp.repository;

import com.example.VideoShareApp.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, ObjectId> {

}
