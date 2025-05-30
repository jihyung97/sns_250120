package com.sns.comment;

import com.sns.comment.service.CommentBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentRestController {

    private final
    CommentBO commentBO;
    @RequestMapping("/create")

    // i: 댓글내용, postId, userId
    public Map<String,Object> createComment(
            @RequestParam("content") String content
           , @RequestParam("postId") int postId
            , HttpSession session

            ){
        Map<String,Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            result.put("code",  500);
            return result;
        }
        commentBO.addComment(postId,userId,content);
        result.put("code",200);
        return result;


    }
}
