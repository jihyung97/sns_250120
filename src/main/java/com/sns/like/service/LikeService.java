package com.sns.like.service;

import com.sns.like.Mapper.LikeMapper;
import com.sns.post.service.PostBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostBO postBO;
    private final LikeMapper likeMapper;
    public Map<String,Object> toggle(int postId, int userId){
        Map<String,Object> result = new HashMap<>();
        if(!postBO.isPostExist(postId)){
            result.put("result", "게시글 없음");
            return result;

        }



        if(likeMapper.selectIsLike(postId, userId) > 0){
            if(likeMapper.deleteLike(postId,userId) > 0){
                result.put("result", "삭제 성공");
            }else{
                result.put("result", "삭제 실패");
            }

        }else{
            if(likeMapper.insertLike(postId,userId) > 0){
                result.put("result", "삽입 성공");
            }else{
                result.put("result", "삽입 실패");
            }


        }
        return result;

    }



}
