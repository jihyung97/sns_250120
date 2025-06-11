package com.sns.timeline.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.dto.CommentDto;
import com.sns.comment.service.CommentBO;
import com.sns.like.service.LikeBO;
import com.sns.post.domain.Post;
import com.sns.post.dto.PostWithComments;
import com.sns.post.service.PostBO;
import com.sns.timeline.dto.CardDto;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@RequiredArgsConstructor
public class TImeLineBO {
    private final CommentBO commentBO;
    private final UserBO userBO;
    private final PostBO postBO;
    private final LikeBO likeBO;
    public List<CardDto> generateCardDtoList(int userId) {

        List<CardDto> cardList = new ArrayList<>();
        //글 목록을 가져온다 List<Post>
        List<Post> postList = postBO.getPostListDescByCreatedAt();


        //cardDto에 postList를 넣는다
        List<CardDto> cardDtoList = new ArrayList<>();
        //post별로 commentList, post,를 넣는다
        for (Post post : postList) {

            CardDto cardDto = new CardDto();
            //post 추가
            cardDto.setPost(post);
            //commentList 추가 ->commentDtoList 추가로 변경
            System.out.println("여기까지!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            List<Comment> commentList = commentBO.getCommentListBypostId(post.getId());
            List<CommentDto> commentDtoList = new ArrayList<>();

            Set<Integer> userIdSet = new HashSet<>();
            for(Comment comment : commentList){
                userIdSet.add(comment.getUserId());
            }
            Map<Integer,String> userIdtoUserName = userBO.getuserIdToUserName(userIdSet);

            //commentDto를 만들어서 List 를 넣는다
            for(Comment comment : commentList){

                CommentDto commentDto = CommentDto.builder()
                        .id(comment.getId())
                        .postId(comment.getPostId())
                        .userId(comment.getUserId())
                        .userName(userIdtoUserName.get(comment.getUserId()))
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdatedAt())
                        .build();
                commentDtoList.add(commentDto);
            }
            cardDto.setComments(commentDtoList);


            //userEntity 추가 (나중에는 Dto로 수정?)
            cardDto.setUserEntity(userBO.getUserEntityByPostId(post.getId()));
            //like 추가
            cardDto.setLikeCount(likeBO.countLike(post.getId()));
            cardDto.setDoILike(likeBO.checkILike(post.getId(),userId));



            cardDtoList.add(cardDto);

        }
        return cardDtoList;


    }
}
