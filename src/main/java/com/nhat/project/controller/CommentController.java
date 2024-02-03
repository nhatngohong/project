package com.nhat.project.controller;

import com.nhat.project.dtomodel.CommentDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentController commentController;
    @PostMapping("/reply")
    public String reply(String content){
        return "a";
    }
    @DeleteMapping("/delete")
    public String delete(){
        return "delete";
    }
    @PutMapping("/edit")
    public String edit(){
        return "edit";
    }
    @PutMapping("/upvote-comment")
    public String upvoteComment(){
        return "upvote";
    }
    @PutMapping("/downvote-comment")
    public String downVote(){
        return "downvote";
    }
}
