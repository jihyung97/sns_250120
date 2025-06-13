package com.sns.friend.service;

import com.sns.friend.entity.FriendEntity;
import com.sns.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FriendBO {

    private final FriendRepository friendRepository;

    public boolean addFriend(int myId, int friendId){


        //친구추가는 양방향으로 추가한다
        try {

            FriendEntity friend1 = FriendEntity.builder()
                    .myId(myId)
                    .friendId(friendId)
                    .build();

            FriendEntity friend2 = FriendEntity.builder()
                    .myId(friendId)
                    .friendId(myId)
                    .build();

            friendRepository.save(friend1);
            friendRepository.save(friend2);

            return true;
        } catch (Exception e) {
            // 로그 남기고 false 반환
            e.printStackTrace(); // or log.error(...)
            return false;
        }






    }


    public FriendEntity getFriend(int myId, int friendId){


        //친구추가는 양방향으로 추가한다
        try {

           FriendEntity friendEntity  =friendRepository.findByMyIdAndFriendId(myId,friendId).orElse(null);

            return friendEntity;
        } catch (Exception e) {
            // 로그 남기고 false 반환
            e.printStackTrace(); // or log.error(...)
            return null;
        }






    }

    public List<FriendEntity> getFriendListByMyId(int myId){
        return friendRepository.findByMyId(myId);
    }
    @Transactional
    public boolean deleteFriend(int myId, int friendId){
        try {



            friendRepository.deleteByMyIdAndFriendId(myId,friendId);
            friendRepository.deleteByMyIdAndFriendId(friendId,myId);

            return true;
        } catch (Exception e) {
            // 로그 남기고 false 반환
            e.printStackTrace(); // or log.error(...)
            return false;
        }
    }


}
