package com.sns.friend;

import com.sns.comment.service.CommentBO;
import com.sns.friend.service.FriendBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendBO friendBO;
    @RequestMapping("/add")
    public Map<String,Object> friendRequest(
                                            @RequestParam("friendId") int friendId
                                            ,HttpSession session

                                            ){
        Map<String,Object> result = new HashMap<>();
        Integer myId = (Integer)session.getAttribute("userId");
        if(myId == null) {
            result.put("result", "세션아이디없음");
            return result;
        }


        if(friendBO.addFriend(myId,friendId) == true){
            result.put("result","성공");

        }else{
            result.put("result","실패");
        }
        return result;

    }

    @DeleteMapping("/delete-friend")
    public Map<String,Object> deleteFriend(@RequestParam("friendId") int friendId,
                                                  HttpSession session
    )
    {
        Map<String,Object> result = new HashMap<>();
        Integer myId = (Integer)session.getAttribute("userId");
        if(myId == null){
            result.put("result","세션아이디없음");
            return result;
        }else{

            if(friendBO.deleteFriend(myId,friendId)){
                result.put("result", "삭제 성공");
            }else{
                result.put("result", "삭제 실패");
            }

        }
        return result;


    }

}
