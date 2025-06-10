package com.sns.friend;

import com.sns.comment.service.CommentBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


//RestController
//@RequestMapping("/friend")
//@RequiredArgsConstructor
//public class FriendRestController {
//
//    private final FriendBO friendBO;
//    @RequestMapping("/friend-request")
//    public Map<String,Object> friendRequest(@RequestParam("friendName") String friendName){
//
//        Map<String,Object> result = friendBO.searchFriendName(friendName);
//    }
//
//}
