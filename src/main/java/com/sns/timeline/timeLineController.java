package com.sns.timeline;

import com.sns.comment.domain.Comment;
import com.sns.comment.service.CommentBO;
import com.sns.friend.dto.FriendDto;
import com.sns.friend.service.FriendBO;
import com.sns.friend.service.FriendRequestService;
import com.sns.friend.service.FriendService;
import com.sns.friendRequest.dto.FriendRequestDto;
import com.sns.friendRequest.entity.FriendRequestEntity;
import com.sns.post.domain.Post;
import com.sns.post.dto.PostWithComments;
import com.sns.post.service.PostBO;
import com.sns.post.service.PostService;
import com.sns.timeline.dto.CardDto;
import com.sns.timeline.service.TImeLineBO;
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
    private final PostService postService;
    private final TImeLineBO tImeLineBO;
    private final FriendRequestService friendRequestService;
    private final FriendBO friendBO;
    private final FriendService friendService;
    @RequestMapping("/timeline")
    public String timeLineView(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId == null){
            return("redirect:/user/sign-in-view");
        }
//       List<PostWithComments> postWithCommentsList = postService.getPostWithCommentsByUserId(userId);
//        List<Post> postList = postBO.getPostListByUserId(userId);
        List<CardDto> cardDtoList = tImeLineBO.generateCardDtoList(userId);
        List<FriendRequestDto> myFriendRequestList = friendRequestService.getMyFriendRequestDtoList(userId);
        List<FriendRequestDto> FriendRequestList = friendRequestService.getFriendRequestDtoList(userId);
        List<FriendDto> FriendDtoList  = friendService.getFriendDtoList(userId);

        model.addAttribute("cardDtoList",cardDtoList);
        model.addAttribute("myFriendRequestList", myFriendRequestList);
        model.addAttribute("FriendRequestList", FriendRequestList);
        model.addAttribute("FriendDtoList", FriendDtoList);

        return("timeline/timeline");
    }

}
