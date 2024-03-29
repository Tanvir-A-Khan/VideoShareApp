package com.example.VideoShareApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String userId;

    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Reference
    private List<Video> uploadedVideos;
}
