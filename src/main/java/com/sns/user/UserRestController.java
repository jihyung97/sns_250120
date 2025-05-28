package com.sns.user;

import com.sns.user.entity.UserEntity;
import com.sns.user.service.UserBO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Map<String,Object>  signUp(
            @RequestParam("loginId") String loginId
            , @RequestParam("password") String password
            , HttpServletRequest request
            ){
        Map<String,Object> result = new HashMap<>();
       UserEntity userEntity  = userBO.getUserEntityByLoginIdAndPassword(loginId,password);
        if(userEntity != null){
            HttpSession session = request.getSession();
            session.setAttribute("userId", userEntity.getId());
            session.setAttribute("userName", userEntity.getName());
            session.setAttribute("userLoginId", userEntity.getLoginId());
            result.put("code", 200);
        }else{
            result.put("code", 500);
            result.put("error_message","사용자가 존재하지 않습니다");
        }
        return result;
    }


}
