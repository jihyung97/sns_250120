package com.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    //localhost:8080/user/sign-in-view
    @GetMapping("/sign-in-view")
    public String login(){
        return "user/signIn";

    }
    //localhost:8080/user/sign-up-view
    @GetMapping("/sign-up-view")
    public String signUp(){
        return "user/signUp";

    }
}
