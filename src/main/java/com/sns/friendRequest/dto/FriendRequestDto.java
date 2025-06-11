package com.sns.friendRequest.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FriendRequestDto {

    private int myId;
    private int friendId;
    private String name;
    private LocalDateTime createdAt;


}