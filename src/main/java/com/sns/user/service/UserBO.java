package com.sns.user.service;

import com.sns.common.HashUtils;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.Null;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserBO {
    private final UserRepository userRepository;

    public Boolean isLoginIdDuplicated(String loginId){
       // return userRepository.findByLoginId(loginId).isEmpty();
        UserEntity user = userRepository.findByLoginId(loginId).orElse(null);
        return user != null;
    }
    public Boolean addUser(
            String loginId
            ,String password
            ,String name
            ,String email
    ){
        String hashedPassword = HashUtils.md5(password);

        UserEntity user = userRepository.save(UserEntity.builder()
                        .loginId(loginId)
                        .password(hashedPassword)
                        .name(name)
                        .email(email)
                .build());
        return user != null;
    }

    public UserEntity getUserEntityByLoginIdAndPassword(String loginId, String password){
        String hashedPassword = HashUtils.md5(password);

        return userRepository.findByLoginIdAndPassword(loginId,hashedPassword).orElse(null);
    }

    public Map<Integer,String> getuserIdToUserName(Set<Integer> userIdOfPostAndComment)
    {
        Map<Integer,String> userIdToUserName = new HashMap<>();
        List<Integer> userIds = new ArrayList<>(userIdOfPostAndComment);
        List<UserEntity> users = userRepository.findByIdIn(userIds);
        for (UserEntity user : users){
            userIdToUserName.put(user.getId(),user.getName());

        }
        return userIdToUserName;
    }

    public Integer getUserIdByUserName(String userName)
    {
        UserEntity user = userRepository.findByName(userName).orElse(null);
        if(user == null){
            return null;

        }else{
            return user.getId();
        }
    }
}
