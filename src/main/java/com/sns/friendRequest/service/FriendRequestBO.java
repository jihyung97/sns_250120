//package com.sns.friendRequest.service;
//
//import com.sns.friendRequest.entity.FriendRequestEntity;
//import com.sns.friendRequest.repository.FriendRequestRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FriendRequestBO {
//    private final FriendRequestRepository friendRequestRepository;
//    public boolean checkFriendRequestExist(int myId,int friendId){
//      return  friendRequestRepository.findByMyIdAndFriendId(myId, friendId).isPresent();
//
//    }
//    public int addFriendRequest(int myId,int friendId){
//
//        FriendRequestEntity friendRequestEntity = FriendRequestEntity
//                .builder()
//                .myId(myId)
//                .friendId(friendId)
//                .build();
//        return friendRequestRepository.save(friendRequestEntity).getId();
//
//    }
//
//    public List<FriendRequestEntity> getFriendRequestListByMyId(int myId){
//        return friendRequestRepository.findByMyId(myId);
//    }
//    public List<FriendRequestEntity> getFriendRequestListByFriendId(int friendId){
//        return friendRequestRepository.findByFriendId(friendId);
//    }
//}
