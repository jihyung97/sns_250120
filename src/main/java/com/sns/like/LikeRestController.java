package com.sns.like;

import com.sns.like.service.LikeBO;
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
    @GetMapping("/like/{postId}")
    public Map<String,Object> likeToggle(
            @PathVariable(name = "postId") int postId,
            HttpSession session
    ) {

        Map<String,Object> result = new HashMap<>();
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId == null){
            result.put("result","로그인안됨");
            return result;
        }

        int rowCount = likeBO.toggle(postId,userId);
        if (rowCount > 0){
            result.put("result", "성공");
        }else{
            result.put("result", "실패");
        }
        return result;
    }



}
