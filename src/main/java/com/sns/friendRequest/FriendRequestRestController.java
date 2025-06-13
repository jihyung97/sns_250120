package com.sns.friendRequest;

import com.sns.friend.service.FriendRequestService;
import com.sns.friendRequest.service.FriendRequestBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend-request")
public class FriendRequestRestController {
    private final FriendRequestBO friendRequestBO;
    private final FriendRequestService friendRequestService;
    @GetMapping("/add-friend-request")
    public Map<String,Object> addFriendRequest(@RequestParam("friendName") String friendName,
                                               HttpSession session
                                               )
    {
        Map<String,Object> result = new HashMap<>();
        Integer myId = (Integer)session.getAttribute("userId");
        if(myId == null){
            result.put("result","세션아이디없음");
            return result;
        }else{
            Map<String,Object> resultOfService = friendRequestService.searchFriend(myId,friendName);
            if(resultOfService == null){
                result.put("result", "friendRequestService 조회 실패");
            }else{
                result.put("result", (String)resultOfService.get("result"));
            }

        }
        return result;


    }

    @DeleteMapping("/delete-myfriend-request")
    public Map<String,Object> deleteFriendRequest(@RequestParam("friendId") int friendId,
                                               HttpSession session
    )
    {
        Map<String,Object> result = new HashMap<>();
        Integer myId = (Integer)session.getAttribute("userId");
        if(myId == null){
            result.put("result","세션아이디없음");
            return result;
        }else{
            int rowCount = friendRequestBO.deleteFriendRequestByMyIdAndFriendId(myId,friendId);
            if(rowCount > 0){
                result.put("result", "요청 삭제");
            }else{
                result.put("result", "삭제 실패");
            }

        }
        return result;


    }

//    public Map<String,Object> getFriendRequestList(
//                                               HttpSession session
//    )
//    {
//        Map<String,Object> result = new HashMap<>();
//        Integer myId = (Integer)session.getAttribute("userId");
//        if(myId == null){
//            result.put("result","세션아이디없음");
//            return result;
//        }else{
//            Map<String,Object> resultOfService = friendRequestService.searchFriend(myId,friendName);
//            if(resultOfService == null){
//                result.put("result", "friendRequestService 조회 실패");
//            }else{
//                result.put("result", (String)resultOfService.get("result"));
//            }
//
//        }
//        return result;
//
//
//    }
}
