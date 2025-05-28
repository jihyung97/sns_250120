package com.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class PostController {
    //localhost:8080/post/post-list-view
    @GetMapping("/post-list-view")
    public String postListView(){
        return "post/timeline";

    }

}
