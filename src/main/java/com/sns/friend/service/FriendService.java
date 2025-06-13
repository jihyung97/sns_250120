package com.sns.friend.service;

import com.sns.friend.dto.FriendDto;
import com.sns.friend.entity.FriendEntity;
import com.sns.friendRequest.service.FriendRequestBO;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final UserBO userBO;
    private final FriendBO friendBO;
    private final FriendRequestBO friendRequestBO;

    public boolean addFriendAndDeleteRequest(int myId, int friendId){
        try {
            friendBO.addFriend(myId,friendId);
            friendRequestBO.deleteFriendRequestByMyIdAndFriendId(myId,friendId);
            friendRequestBO.deleteFriendRequestByMyIdAndFriendId(friendId,myId);

            return true;
        } catch (Exception e) {
            // 로그 남기고 false 반환
            e.printStackTrace(); // or log.error(...)
            return false;
        }

    }

    public List<FriendDto> getFriendDtoList(int myId){

        //friend 요청 리스트의 친구Id항목을 내 아이디가 되게 셀렉트 -> 상대방이 나에게 건 친구요청
        List<FriendEntity> friendEntityList = friendBO.getFriendListByMyId(myId);
        Set<Integer> IdSet  = new HashSet<>();
        for(FriendEntity friendEntity : friendEntityList){
            IdSet.add(friendEntity.getFriendId());
        }
        Map<Integer,String> idToName = userBO.getuserIdToUserName(IdSet);



        List<FriendDto> friendDtoList = new ArrayList<>();
        for(FriendEntity friendEntity : friendEntityList){
            FriendDto friendDto = FriendDto.builder()
                    .myId(friendEntity.getMyId())
                    .friendId(friendEntity.getFriendId())
                    //friendId로 name을 찾을 수 없으면 탈퇴한 유저로 나오게 함
                    .name(idToName.getOrDefault(friendEntity.getFriendId(),"탈퇴한 유저"))
                    .createdAt(friendEntity.getCreatedAt())
                    .build();
            friendDtoList.add(friendDto);

        }
        return friendDtoList;
    }
//    private Map<String,Object> addFriend(int myId, ){
//        Map<String,Object> result = new HashMap<>();
//        Integer userId = userBO.getUserIdByUserName(userName);
//        if(userId == null){
//            result.put( "result","아이디없음");
//            return result;
//        }
//        friendBO.
//
//    }



}
