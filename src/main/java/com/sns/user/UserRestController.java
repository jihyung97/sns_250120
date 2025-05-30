package com.sns.user;

import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
    private final UserBO userBO;

    @GetMapping("/is-id-duplicated")
    public Map<String,Object> isIdDuplicated(
            @RequestParam("loginId") String loginId
    ){
        Map<String,Object> result = new HashMap<>();
        boolean isDuplicated = userBO.isLoginIdDuplicated(loginId);
        result.put("is_id_duplicated",isDuplicated);
        result.put("code",200);
        return result;

    }


    @PostMapping("/sign-up")
    public Map<String,Object>  signUp(
         @RequestParam("loginId") String loginId
         , @RequestParam("password") String password
         , @RequestParam("name") String name
         , @RequestParam("email") String email

    ){
         Map<String,Object> result = new HashMap<>();
         boolean isSuccess = userBO.addUser(loginId,password,name,email);
        if(isSuccess){
            result.put("code", 200);
        }else{
            result.put("code", 500);
            result.put("error_message","회원가입이 정상적으로 진행되지 않습니다");
        }
        return result;
    }

    @PostMapping("/sign-in")
    public Map<String,Object>  signIn(
            @RequestParam("loginId") String loginId
            , @RequestParam("password") String password
            , HttpServletRequest request
            ){
        Map<String,Object> result = new HashMap<>();
        HttpSession session =  request.getSession();
        UserEntity user = userBO.getUserEntityByLoginIdAndPassword(loginId,password);
        if(user == null){
            result.put("code", 300);
            result.put("error_message", "존재하지 않는 사용자 입니다");
            return result;
        }
        session.setAttribute("userId", user.getId());
        System.out.println(user.getId());
        System.out.println(user.getLoginId() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        session.setAttribute("userName", user.getName());
        session.setAttribute("userLoginId", user.getLoginId());
        result.put("code",200);
        return result;
    }


}
