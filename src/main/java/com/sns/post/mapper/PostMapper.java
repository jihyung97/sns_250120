package com.sns.post.mapper;

import com.sns.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    public List<Post> selectPostListByUserId(int userId);
    public int insertPost(@Param("userId") int userId
            ,@Param("content") String content
            ,@Param("imagePath") String imagePath);


    public List<Post> selectPostListDescByCreatedAt();

}