package com.sns.timeline;

import com.sns.friend.dto.FriendDto;
import com.sns.friendRequest.dto.FriendRequestDto;
import com.sns.post.service.PostBO;
import com.sns.timeline.dto.CardDto;
import com.sns.timeline.service.TImeLineBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/timeline")
@RequiredArgsConstructor
public class TimeLineRestController {
    private final TImeLineBO tImeLineBO;
    private final PostBO postBO;


    @DeleteMapping("/delete-card")
    public Map<String,Object> deleteCard(HttpSession session
            , @RequestParam("postId" )int postId
    )

    {
        Map<String,Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            result.put("result","로그인하고 삭제하세요");
        }
        else{
            if(tImeLineBO.deleteCard(postId)){
                result.put("result","삭제 성공");
            }else{

                result.put("result", "삭제 실패");
            }

        }
        return result;


//
    }
}
