package com.sns.timeline.dto;

import com.sns.comment.domain.Comment;
import com.sns.comment.dto.CommentDto;
import com.sns.post.domain.Post;
import com.sns.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.catalina.User;

import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class CardDto {
    private List<CommentDto> comments;
    private Post post;
    private UserEntity userEntity;
    private int likeCount;
    private boolean doILike;




}
