package com.sns.like.Mapper;

import com.sns.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeMapper
{
    public int selectIsLike(@Param("postId") int postId, @Param("userId") int userId);
    public int selectLikeCount(int postId);
    public int deleteLike(@Param("postId") int postId, @Param("userId") int userId);
    public int insertLike(@Param("postId") int postId, @Param("userId") int userId);
    public int deleteLikeByPostId(int postId);
}