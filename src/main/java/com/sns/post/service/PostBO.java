package com.sns.post.service;

import com.sns.comment.domain.Comment;
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
    public List<Post>  getPostListDescByCreatedAt(){return postMapper.selectPostListDescByCreatedAt();}



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

    public boolean isPostExist(int postId)  {
        return postMapper.countPostById(postId) > 0 ? true: false;
    }

    public int removePostById(int postId){return postMapper.deletePostById(postId);}

    public Post getPostById(int postId){return postMapper.selectPostById(postId);}


}
