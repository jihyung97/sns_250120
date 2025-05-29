package com.sns.post;

import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRestController {

    private final PostBO postBO;


    @PostMapping ("/create")
    public Map<String,Object> createPost(
            @RequestParam("content") String content
            , HttpServletRequest request
    )
        {
            HttpSession session = request.getSession();
            int userId =  (int)session.getAttribute("userId");

            int rowCount = postBO.addPost(userId,content,null);

            Map<String,Object> result = new HashMap<>();
            if(rowCount > 0){
                result.put("code",200);
            }else{
                result.put("code",500);
                result.put("error_message","게시글을 저장하지 못했습니다");
            }
            return result;



        }
}
