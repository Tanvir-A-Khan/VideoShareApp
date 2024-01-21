package com.example.VideoShareApp.repository;

import com.example.VideoShareApp.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends MongoRepository<Video, String> {
    List<Video> findByUploaderName(String uploaderName);
}
