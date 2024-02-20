package com.nhat.project.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.nhat.project.dto.post.PostDto;
import com.nhat.project.dto.post.PostShowDto;
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
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
//    @GetMapping("/register")
//    public ResponseEntity<?> register(@RequestBody UserDto userDto){
//
//
//
//    }

    @GetMapping
    public ResponseEntity<?> showPost(@RequestParam int page){
        List<PostShowDto> postDtoList = postService.showPost(page);
        return ResponseEntity.status(HttpStatus.OK).body(postDtoList);
    }
}
