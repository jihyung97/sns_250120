package com.sns.timeline.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.dto.CommentDto;
import com.sns.comment.service.CommentBO;
import com.sns.post.domain.Post;
import com.sns.post.dto.PostWithComments;
import com.sns.post.service.PostBO;
import com.sns.timeline.dto.CardDto;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class TImeLineBO {
    private final CommentBO commentBO;
    private final UserBO userBO;
    private final PostBO postBO;
    public List<CardDto> generateCardDtoList() {

        List<CardDto> cardList = new ArrayList<>();
        //글 목록을 가져온다 List<Post>
        List<Post> postList = postBO.getPostListDescByCreatedAt();


        //cardDto에 postList를 넣는다
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Post post : postList) {

            CardDto cardDto = new CardDto();
            //post 추가
            cardDto.setPost(post);
            //commentList 추가 ->commentDtoList 추가로 변경
            cardDto.setComments(commentBO.getCommentListBypostId(post.getId()));
            //userEntity 추가 (나중에는 Dto로 수정?)
            cardDto.setUserEntity(userBO.getUserEntityByPostId(post.getId()));
            //like 추가



            cardDtoList.add(cardDto);

        }
        return cardDtoList;


    }
}
