package com.nhat.project.controller;

import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/posts")
    private ResponseEntity<?> showPosts(Authentication authentication,
                                        int page){
        String username = authentication.getName();
        int id = userService.findByUsername(username).getId();
        List<Post> posts = userService.showPost(page,id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    @GetMapping("/comments")
    private ResponseEntity<?> showComments(Authentication authentication,
                                        int page){
        String username = authentication.getName();
        int id = userService.findByUsername(username).getId();
        List<Comment> comments = userService.showComment(page, id);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }
}
