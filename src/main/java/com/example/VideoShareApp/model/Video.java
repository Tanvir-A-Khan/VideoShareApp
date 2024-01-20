package com.example.VideoShareApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "videos")
public class Video {
    @Id
    private String videoId;
    private String videoTitle;
    private String uploaderName;
    private String videoLink;
    private Long viewCount;


    private List<String> likes;

    private List<String> disLikes;


}
