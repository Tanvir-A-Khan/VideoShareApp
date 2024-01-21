package com.example.VideoShareApp.services;

import com.example.VideoShareApp.model.Video;
import com.example.VideoShareApp.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final MongoTemplate mongoTemplate;
    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(MongoTemplate mongoTemplate, VideoRepository videoRepository) {
        this.mongoTemplate = mongoTemplate;
        this.videoRepository = videoRepository;
    }

    public Video saveVideo(Video video) {
        mongoTemplate.save(video);
        return video;
    }

    public List<Video> getAllVideos() {
        return mongoTemplate.findAll(Video.class);
    }

    public Optional<Video> getVideoByIdAndView(String videoId) {
        Optional<Video> optionalVideo = videoRepository.findById(videoId);

        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();

            // Increment the viewCount
            video.setViewCount(video.getViewCount() + 1);

            // Save the updated video
            videoRepository.save(video);

            return Optional.of(video);
        } else {
            // Handle not found scenario
            // You might want to throw an exception or handle it based on your application's logic
            return Optional.empty();
        }
    }
    public Optional<Video> getVideoByIdAndAddLike(String videoId, String userName) {
        Optional<Video> optionalVideo = videoRepository.findById(videoId);

        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            List<String> likes = video.getLikes();
            List<String> dislikes = video.getDisLikes();

            // Check if the user's name is in the likes list
            if (likes.contains(userName)) {
                // If the user already liked, remove from the likes list
                likes.remove(userName);
            } else {

                if(dislikes.contains(userName)){
                    dislikes.remove(userName);
                }
                // If the user didn't like, add to the likes list
                likes.add(userName);
            }

            // Save the updated video
            videoRepository.save(video);

            return Optional.of(video);
        } else {
            // Handle not found scenario
            // You might want to throw an exception or handle it based on your application's logic
            return Optional.empty();
        }
    }

    public Optional<Video> getVideoByIdAndToggleDislike(String videoId, String userName) {
        Optional<Video> optionalVideo = videoRepository.findById(videoId);

        if (optionalVideo.isPresent()) {
            Video video = optionalVideo.get();
            List<String> likes = video.getLikes();
            List<String> dislikes = video.getDisLikes();

            // Check if the user's name is in the dislikes list
            if (dislikes.contains(userName)) {
                // If the user already disliked, remove from the dislikes list
                dislikes.remove(userName);
            } else {
                // If the user didn't dislike, add to the dislikes list
                dislikes.add(userName);

                // If the user already liked, remove from the likes list
                if (likes.contains(userName)) {
                    likes.remove(userName);
                }
            }

            // Save the updated video
            videoRepository.save(video);

            return Optional.of(video);
        } else {
            // Handle not found scenario
            // You might want to throw an exception or handle it based on your application's logic
            return Optional.empty();
        }
    }



}
