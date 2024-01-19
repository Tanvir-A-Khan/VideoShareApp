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
@RequestMapping("/videos")
@CrossOrigin(origins = "http://localhost:5173/")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<Video> saveVideo(@RequestBody Video video) {
        Video savedVideo = this.videoService.saveVideo(video);
        return new ResponseEntity<>(savedVideo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<Video> getVideoById(@PathVariable String videoId) {
        Optional<Video> video = videoService.getVideoById(videoId);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<Video> updateVideo(@PathVariable String videoId, @RequestBody Video video) {
        // Ensure video ID matches in request body
        if (!videoId.equals(video.getVideoId())) {
            return ResponseEntity.badRequest().build();
        }
        videoService.updateVideo(video);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String videoId) {
        videoService.deleteVideo(videoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Video>> searchVideos(@RequestParam String searchTerm) {
        List<Video> videos = videoService.searchVideos(searchTerm);
        return ResponseEntity.ok(videos);
    }
}
