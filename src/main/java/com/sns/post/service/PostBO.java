package com.sns.post.service;

import com.sns.post.domain.Post;
import com.sns.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostBO {
    private final PostMapper postMapper;

    public List<Post> getPostListByUserId(int userId){
        return postMapper.selectPostListByUserId(userId);
    }


    public int addPost(
            int userId
            ,String content
            ,String imagePath
    ){
        return postMapper.insertPost(userId,content,imagePath);
    }

}
