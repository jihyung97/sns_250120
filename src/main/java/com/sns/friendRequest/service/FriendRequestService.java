package com.sns.friend.service;

import com.sns.friendRequest.dto.FriendRequestDto;
import com.sns.friendRequest.entity.FriendRequestEntity;
import com.sns.friendRequest.service.FriendRequestBO;
import com.sns.user.service.UserBO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FriendRequestService {
    private final UserBO userBO;
    private final FriendBO friendBO;

    private final FriendRequestBO friendRequestBO;


    public Map<String,Object> searchFriend(int myId, String friendName){
        Map<String,Object> result = new HashMap<>();
        Integer friendId = userBO.getUserIdByUserName(friendName);
        //검색한 이름이 없는 경우
        if(friendId == null){
            result.put( "result","아이디없음");
            return result;
        }
        if(friendId.equals(myId)){
            result.put("result","내아이디를검색");
            return result;

        }
        //이미 친구 요청을 했는지 확인한다. 또한 상대방측이 요청을 이미 했는지도 확인한다. in: 로그인된 세션의 userId, 친구요청한 친구Id
        boolean isFriendRequestExist = friendRequestBO.checkFriendRequestExist(myId,friendId) || friendRequestBO.checkFriendRequestExist(friendId,myId);

        //이미 친구인지도 확인한다.
        boolean isFriend = friendBO.getFriend(myId, friendId) == null ? false: true;
        if(isFriendRequestExist){
            result.put("result","이미요청됨");
        }else if(isFriend){
            result.put("result", "이미 친구입니다");
        }else{
           int rowCount = friendRequestBO.addFriendRequest(myId,friendId);
           if(rowCount > 0){

               result.put("result", "요청성공");
           }else{
               result.put("result", "요청실패");
           }
        }

        return result;
    }
    // 내가 요청한 친구요청 목록 리스트를 가져옴
    public List<FriendRequestDto> getMyFriendRequestDtoList(int myId){

        List<FriendRequestEntity> friendRequestEntityList = friendRequestBO.getFriendRequestListByMyId(myId);
        Set<Integer> FriendIdSet  = new HashSet<>();
        for(FriendRequestEntity friendRequestEntity : friendRequestEntityList){
            FriendIdSet.add(friendRequestEntity.getFriendId());
        }
       Map<Integer,String> friendIdTofriendName = userBO.getuserIdToUserName(FriendIdSet);



        List<FriendRequestDto> friendRequestDtoList = new ArrayList<>();
        for(FriendRequestEntity friendRequest : friendRequestEntityList){
            FriendRequestDto friendRequestDto = FriendRequestDto.builder()
                    .myId(myId)
                    .friendId(friendRequest.getFriendId())
                    //friendId로 name을 찾을 수 없으면 탈퇴한 유저로 나오게 함
                    .name(friendIdTofriendName.getOrDefault(friendRequest.getFriendId(),"탈퇴한 유저"))
                    .createdAt(friendRequest.getCreatedAt())
                    .build();
            friendRequestDtoList.add(friendRequestDto);

        }
        return friendRequestDtoList;
    }

    // 상대방이 나에게 건 친구요청 목록 리스트를 가져옴
    public List<FriendRequestDto> getFriendRequestDtoList(int myId){

        //friend 요청 리스트의 친구Id항목을 내 아이디가 되게 셀렉트 -> 상대방이 나에게 건 친구요청
        List<FriendRequestEntity> friendRequestEntityList = friendRequestBO.getFriendRequestListByFriendId(myId);
        Set<Integer> IdSet  = new HashSet<>();
        for(FriendRequestEntity friendRequestEntity : friendRequestEntityList){
            IdSet.add(friendRequestEntity.getMyId());
        }
        Map<Integer,String> idToName = userBO.getuserIdToUserName(IdSet);



        List<FriendRequestDto> friendRequestDtoList = new ArrayList<>();
        for(FriendRequestEntity friendRequest : friendRequestEntityList){
            FriendRequestDto friendRequestDto = FriendRequestDto.builder()
                    .myId(friendRequest.getMyId())
                    .friendId(friendRequest.getFriendId())
                    //friendId로 name을 찾을 수 없으면 탈퇴한 유저로 나오게 함
                    .name(idToName.getOrDefault(friendRequest.getMyId(),"탈퇴한 유저"))
                    .createdAt(friendRequest.getCreatedAt())
                    .build();
            friendRequestDtoList.add(friendRequestDto);

        }
        return friendRequestDtoList;
    }



}