//package com.sns.test;
//
//import com.sns.post.domain.Post;
//import com.sns.post.mapper.PostMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//public class TestController {
//
//    @Autowired
//    PostMapper postMapper;
//
//    @ResponseBody
//    @GetMapping("/test4")
//    public List<Post> test4(){
//        return postMapper.selectPostListTest();
//    }
//}
