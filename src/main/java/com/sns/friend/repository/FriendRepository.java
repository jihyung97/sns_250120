package com.sns.friend.repository;

import com.sns.friend.entity.FriendEntity;
import com.sns.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FriendRepository extends JpaRepository<FriendEntity,Integer> {
    List<FriendEntity> findByMyId(int myId);

    int deleteByMyIdAndFriendId(int myId, int friendId);
    Optional<FriendEntity> findByMyIdAndFriendId(int myId, int friendId);


}