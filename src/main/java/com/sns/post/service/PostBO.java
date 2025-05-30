package com.sns.post.service;

import com.sns.common.FileManagerService;
import com.sns.post.domain.Post;
import com.sns.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostBO {
    private final PostMapper postMapper;
    private final FileManagerService fileManager;

    public List<Post> getPostListByUserId(int userId){
        return postMapper.selectPostListByUserId(userId);
    }


    public int addPost(
            int userId
            , String userLoginId
            , String content
            , MultipartFile file
            ){
        String imagePath = null;
        if(file != null){
            imagePath = fileManager.uploadFile(file,userLoginId);
        }

        return postMapper.insertPost(userId,content,imagePath);
    }

}
