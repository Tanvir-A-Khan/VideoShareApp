package com.example.VideoShareApp.controller;

import com.example.VideoShareApp.model.Video;
import com.example.VideoShareApp.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/videos")
@CrossOrigin(origins = "http://localhost:5173/")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/add-a-video")
    public ResponseEntity<Video> saveVideo(@RequestBody Video video) {
        Video savedVideo = this.videoService.saveVideo(video);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-videos")
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<Video> getVideoByIdAndView(@PathVariable String videoId) {
        try {
            Optional<Video> video = videoService.getVideoByIdAndView(videoId);
            System.out.println(videoId);
            return video.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception or handle it based on your application's logic
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/{videoId}/addLike/{userName}")
    public ResponseEntity<Video> addLike(@PathVariable String videoId, @PathVariable String userName) {
        Optional<Video> updatedVideo = videoService.getVideoByIdAndAddLike(videoId, userName);
        return updatedVideo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{videoId}/toggle-dislike/{userName}")
    public ResponseEntity<Video> toggleDislike(
            @PathVariable String videoId,
            @PathVariable String userName) {
        System.out.println("gotcha");
        Optional<Video> updatedVideo = videoService.getVideoByIdAndToggleDislike(videoId, userName);

        return updatedVideo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
