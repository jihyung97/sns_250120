//package com.sns.friend.service;
//
//import com.sns.user.repository.UserRepository;
//import com.sns.user.service.UserBO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class friendService {
//    private final UserBO userBO;
//    private final FriendBO friendBO;
//
//    private Map<String,Object> searchFriend(int currentUserId, String userName){
//        Map<String,Object> result = new HashMap<>();
//        Integer userId = userBO.getUserIdByUserName(userName);
//        if(userId == null){
//            result.put( "result","아이디없음");
//            return result;
//        }
//        friendBO.
//
//    }
//
//
//
//}
