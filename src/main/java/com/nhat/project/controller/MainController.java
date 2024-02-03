package com.nhat.project.controller;

import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;
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
    @GetMapping("/comments")
    private String comments(){
        return "comments";
    }
}
