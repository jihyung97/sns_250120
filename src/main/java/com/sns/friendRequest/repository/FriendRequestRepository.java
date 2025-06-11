//package com.sns.friendRequest.repository;
//
//import com.sns.friend.entity.FriendEntity;
//import com.sns.friendRequest.entity.FriendRequestEntity;
//import com.sns.user.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;

//public interface FriendRequestRepository extends JpaRepository<FriendRequestEntity,Integer> {
//    public Optional<FriendRequestEntity> findByMyIdAndFriendId(int myId, int friendId);
//    public List<FriendRequestEntity> findByMyId(int myId);
//    public List<FriendRequestEntity> findByFriendId(int friendId);
//
//
//
//
//}