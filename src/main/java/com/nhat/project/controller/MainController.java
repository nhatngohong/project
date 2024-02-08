package com.nhat.project.controller;

import com.nhat.project.entity.Post;
import com.nhat.project.exception.PostNotFoundException;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/user")
    private String posts(){
        return "user";
    }
    @GetMapping("/posts/sort-by-upvote/page/{id}")
    public ResponseEntity<?> showPostByUpvote(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.showPostByUpvote(id));
    }
    @GetMapping("/posts/sort-by-date/page/{id}")
    public ResponseEntity<?> showPostByDate(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.showPostByDate(id));
    }

}
