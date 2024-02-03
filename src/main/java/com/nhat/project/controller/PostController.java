package com.nhat.project.controller;

import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;
    @PostMapping("/ask")
    public String ask(){
        return "ask";
    }
    @DeleteMapping("/delete-post")
    private String deletePost(){
        return "delete";
    }
    @PutMapping("/update-post")
    private String updatePost(){
        return "update";
    }
    @PutMapping("/upvote-post")
    public String upvoteComment(){
        return "upvote";
    }
    @PutMapping("/downvote-post")
    public String downVote(){
        return "downvote";
    }
}
