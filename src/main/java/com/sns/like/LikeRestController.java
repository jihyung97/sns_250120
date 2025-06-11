package com.sns.like;

import com.sns.like.service.LikeBO;
import com.sns.like.service.LikeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LikeRestController {
    private final LikeBO likeBO;
    private final LikeService likeService;
    @GetMapping("/like/{postId}")
    public Map<String,Object> likeToggle(
            @PathVariable(name = "postId") int postId,
            HttpSession session
    ) {
        Map<String,Object> result = new HashMap<>();
       // Map<String,Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId == null){
            result.put("result","로그인안됨");
            return result;

        }
        result =   likeService.toggle(postId,userId);
        return result;
    }



}
