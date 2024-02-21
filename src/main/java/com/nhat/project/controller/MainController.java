package com.nhat.project.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.nhat.project.dto.post.PostDto;
import com.nhat.project.dto.post.PostShowDto;
import com.nhat.project.dto.user.UserCreateDto;
import com.nhat.project.entity.User;
import com.nhat.project.exception.UsernameAlreadyTaken;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("")
    public String greet(){
        return "hi";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDto userCreateDto){
        try{
            userService.register(userCreateDto);
            return ResponseEntity.status(HttpStatus.OK).body(userCreateDto);
        }
        catch (UsernameAlreadyTaken usernameAlreadyTaken){
            return ResponseEntity.status(HttpStatus.OK).body(usernameAlreadyTaken);
        }
    }
    @GetMapping("/home/posts")
    public ResponseEntity<?> showPost(@RequestParam int page){
        List<PostShowDto> postDtoList = postService.showPost(page);
        return ResponseEntity.status(HttpStatus.OK).body(postDtoList);
    }
}
