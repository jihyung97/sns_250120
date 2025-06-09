package com.sns.post.dto;

import com.sns.comment.domain.Comment;
import com.sns.comment.dto.CommentDto;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostWithComments {
    private int postId;
    private int userId;
    private String loginId;
    private String content;
    private String imagePath;
    private List<CommentDto> comments;

}
