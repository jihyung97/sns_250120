package com.sns.timeline;

import com.sns.comment.domain.Comment;
import com.sns.comment.service.CommentBO;
import com.sns.post.domain.Post;
import com.sns.post.service.PostBO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class timeLineController {
    private final PostBO postBO;
    private final CommentBO commentBO;
    @RequestMapping("/timeline")
    public String timeLineView(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return("redirect:/user/sign-in-view");
        }

        List<Post> postList = postBO.getPostListByUserId(userId);
        List<Comment> commentList = commentBO.getCommentListByPostId;

        model.addAttribute("postList",postList);
        System.out.println(postList + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return("timeline/timeline");
    }

}
