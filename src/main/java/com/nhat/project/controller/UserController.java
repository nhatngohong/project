package com.nhat.project.controller;

import com.nhat.project.dtomodel.CommentDto;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;
    @GetMapping("/posts")
    private String posts(){
        return "posts";
    }
    @GetMapping("/comments")
    private String comments(){
        return "comments";
    }
    @GetMapping("/saved")
    private String saved(){
        return "saved";
    }
}
