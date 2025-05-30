package com.sns.post;

import com.sns.common.FileManagerService;
import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            ,@RequestParam(value = "file", required = false) MultipartFile image
            , HttpSession session
    )
        {

            int userId =  (int)session.getAttribute("userId");
            String userLoginId = (String)session.getAttribute("userLoginId");

            //userId,userLoginId는 session에서 content, image는 jsp에서 가져온다
            int rowCount = postBO.addPost(userId,userLoginId,content,image);

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
