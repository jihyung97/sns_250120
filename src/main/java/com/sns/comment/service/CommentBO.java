package com.sns.comment.service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mapper.CommentMapper;
import com.sns.post.domain.Post;
import com.sns.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentBO {

    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    public int addComment(int postId, int userId, String content) {

        return commentMapper.insertComment(postId, userId, content);

    }

    public List<Comment> getCommentListBypostIds(List<Integer> postIds) {

        return commentMapper.selectCommentListByPostIds(postIds);

    }

    public int removeCommentById(int commentId) {
       return commentMapper.deleteCommentById(commentId);
    }
    public List<Comment> getCommentListBypostId(int postId){return commentMapper.selectCommentListByPostId;}

//    public Map<Integer , List<Comment>> selectCommentByPostIdOfUserId (int UserId){
//        List<Post> PostList = postMapper.selectPostByUserId(userId);  //userId로 Post를 내림차순으로 가져온다
//        List< List<Comment>> commentListByPostIdOfUserId = new HashMap<>(); // 가져온 포스트 별 댓글리스트를 생성
//        for(Post post : PostList ){ // post리스트를 postId로 순회
//            int postId = post.getId();
//            List<Comment> commentList= selectCommentListByPostId(postId); // postId 별 댓글리스트를 저장
//            //comment Table에 postId를 인덱스로 설정하였기 때문에 검색속도 빠르다.
//           commentListByPostIdOfUserId.add(commentList);
//
//        }
//    }
}
