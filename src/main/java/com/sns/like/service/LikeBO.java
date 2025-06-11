package com.sns.like.service;


import com.sns.like.Mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeBO {

    private final LikeMapper likeMapper;
    public int toggle(int postId, int userId){
        Map<String,Object> result = new HashMap<>();
        int rowCount =likeMapper.selectIsLike(postId, userId);
        int count = 0;
        if(rowCount > 0){
             count = likeMapper.deleteLike(postId,userId);

        }else{
             count = likeMapper.insertLike(postId,userId);


        }
        return count;

    }

    public boolean checkILike(int postId, int userId){
        return likeMapper.selectIsLike(postId,userId) > 0 ? true : false;
    }


    public int countLike(int postId){
        return likeMapper.selectLikeCount(postId);
    }

}
