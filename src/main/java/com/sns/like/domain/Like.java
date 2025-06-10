package com.sns.like.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Like {
    private int postId;
    private int userId;

    private LocalDateTime createdAt;

}