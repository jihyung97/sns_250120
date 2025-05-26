package com.sns.post.domain;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Post {
    private int id;
    private int userId;
    private String content;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
