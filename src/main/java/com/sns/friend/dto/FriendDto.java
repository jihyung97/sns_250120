package com.sns.friend.dto;

import lombok.*;

import java.time.LocalDateTime;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FriendDto {



        private int myId;
        private int friendId;
        private String name;
        private LocalDateTime createdAt;


    }

