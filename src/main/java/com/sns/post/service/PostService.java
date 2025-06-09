package com.sns.post.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.dto.CommentDto;
import com.sns.comment.service.CommentBO;
import com.sns.post.domain.Post;
import com.sns.post.dto.PostWithComments;
import com.sns.user.dto.UserNameDTO;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@Service
@RequiredArgsConstructor
public class PostService {
    private final CommentBO commentBO;
    private final UserBO userBO;
    private final PostBO postBO;
    public List<PostWithComments> getPostWithCommentsByUserId(int userId){
        // userId에 따른 post의 list
       List<Post> postList = postBO.getPostListByUserId(userId);
       if(postList == null){
           postList = new ArrayList<>();
       }
       List<Integer> postIds = new ArrayList<>();

       //가져온 post list들의 postid list

            for(Post post : postList){
                postIds.add(post.getId());
            }



       //post id list로 전체 comment들 entity (postId에 관계없이) 다 가져오기(postId별 분류는 BO에서)
        //null 처리 필요


        List<Comment> commentList = commentBO.getCommentListBypostIds(postIds);
        if(commentList == null){
            commentList = new ArrayList<>();
        }


       // 게시글 작성자 이름과 댓글작성자 이름을 알기 위해 userId 의 set(중복방지)을 만든다

        Set<Integer> userIdOfPostAndComment = new HashSet<>();

        //commentList에서 userId 들을 가져온다

            for(Comment comment : commentList) {
                userIdOfPostAndComment.add(comment.getUserId());
            }



        //post list 에서 userId들을 가져온다

            for(Post post : postList) {
                userIdOfPostAndComment.add(post.getUserId());
            }


        //userIdSet으로 userName list를 select, 즉 post와 comment 의 userid를 합쳐서 한번에 BO에서 userName 리스트를 가져오게 한다. (쿼리문축소)
        //null 처리 필요
        Map<Integer, String> userIdToUserName;

        if (!userIdOfPostAndComment.isEmpty()) {
            userIdToUserName = userBO.getuserIdToUserName(userIdOfPostAndComment);
            if (userIdToUserName == null) {
                userIdToUserName = new HashMap<>();
            }
        } else {
            userIdToUserName = new HashMap<>();
        }



        // postId와 commentList 가 pair가 되는 map, comment list를 순회하면서 commentDto에 userName을 넣고 map에 데이터 입력
        Map<Integer, List<CommentDto>> postIdToCommentsMap = new HashMap<>();

        for(Comment comment : commentList){
            CommentDto commentDto = CommentDto.builder()
                    .id(comment.getId())
                    .postId(comment.getPostId())
                    .userId(comment.getUserId())
                    .userName(userIdToUserName.get(comment.getUserId()))
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .updatedAt(comment.getUpdatedAt())
                    .build();


            postIdToCommentsMap.computeIfAbsent(comment.getPostId(), k->new ArrayList<>()).add(commentDto);
        }

        //post별로 postwithcomments dto에 property 채우기


        List<PostWithComments> postWithCommentsList = new ArrayList<>();
        for(Post post:postList){
            PostWithComments postWithComments  = PostWithComments.builder()
                    .postId(post.getId())
                    .userId(post.getUserId())
                    .loginId(userIdToUserName.get(post.getUserId()))
                    .content(post.getContent())
                    .imagePath(post.getImagePath())
                    . comments(postIdToCommentsMap.getOrDefault(post.getId(), Collections.emptyList()))
                    .build();
            postWithCommentsList.add(postWithComments);

        }
        return postWithCommentsList;




    }
}
