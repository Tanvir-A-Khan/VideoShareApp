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

    @Autowired
    public VideoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Video saveVideo(Video video) {
        mongoTemplate.save(video);
        return video;
    }

    public List<Video> getAllVideos() {
        return mongoTemplate.findAll(Video.class);
    }

    public Optional<Video> getVideoById(String videoId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("videoId").is(videoId));
        return Optional.ofNullable(mongoTemplate.findOne(query, Video.class));
    }
    public void updateVideo(Video video) {
        mongoTemplate.save(video);
    }

    public void deleteVideo(String videoId) {
        Query query = new Query(Criteria.where("videoId").is(videoId));
        mongoTemplate.remove(query, Video.class);
    }

    public List<Video> searchVideos(String searchTerm) {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("videoTitle").regex(searchTerm, "i"),
                Criteria.where("uploaderName").regex(searchTerm, "i")
        ));
        return mongoTemplate.find(query, Video.class);
    }


    /// =================================================================================================
//    @Autowired
//    private VideoRepository videoRepository;
//
//    // Existing methods...
//
//    public void addUserNameToLikes(String videoId, String userName) {
//        Optional<Video> optionalVideo = videoRepository.findById(videoId);
//
//        if (optionalVideo.isPresent()) {
//            Video video = optionalVideo.get();
//            List<String> likes = video.getLikes();
//
//            // Check if the userName is already in the likes list
//            if (!likes.contains(userName)) {
//                likes.add(userName);  // Add the userName to the likes list
//                videoRepository.save(video);  // Save the updated video to the repository
//            }
//        }
//    }

}
