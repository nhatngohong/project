package com.nhat.project.controller;

import com.nhat.project.dto.post.PostShowDto;
import com.nhat.project.dto.user.UserDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.not_found.UserNotFoundException;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/posts/{id}")
    private ResponseEntity<?> getUserPost(@PathVariable int id,
                                          @RequestParam int page,
                                          @RequestParam(defaultValue = "latest") String sortType){
        List<PostShowDto> postShowDtos = userService.getUserPost(id,page,sortType);
        return ResponseEntity.status(HttpStatus.OK).body(postShowDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(Authentication authentication,
                                     @PathVariable int id){
        UserDto info = userService.getInfo(authentication.getName(),id);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }
}
