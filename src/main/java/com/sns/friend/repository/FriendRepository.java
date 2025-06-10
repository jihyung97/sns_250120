//package com.sns.friend.repository;
//
//import com.sns.friend.entity.FriendEntity;
//import com.sns.user.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//
//public interface FriendRepository extends JpaRepository<FriendEntity,Integer> {
//    public Optional<FriendEntity> findByMyId(String loginId);
//    public Optional<UserEntity> findByLoginIdAndPassword(String loginId, String password);
//    List<UserEntity> findByIdIn(List<Integer> userIds);
//}