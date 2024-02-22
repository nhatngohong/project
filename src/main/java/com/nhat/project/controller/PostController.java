package com.nhat.project.controller;

import com.nhat.project.dto.post.*;
import com.nhat.project.entity.User;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.not_found.PostNotFoundException;
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
@RequestMapping("/post")
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping("/ask")
    public ResponseEntity<?> ask(Authentication authentication,
                                 @RequestBody PostCreateDto postCreateDto){
        String title = postCreateDto.getTitle();
        String content = postCreateDto.getContent();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        postService.ask(content,title,user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PostCreateDto(title,content,user.convertToDto())
        );
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deletePost(Authentication authentication,
                                              @PathVariable int id){
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        PostDeleteDto postDeleteDto = postService.delete(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(postDeleteDto);
        //TODO fix
    }
    @PutMapping("/edit/{id}")
    private ResponseEntity<?> updatePost(Authentication authentication,
                                              @PathVariable int id,
                                              @RequestBody PostCreateDto postUpdateDto){

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        PostUpdateDto postUpdateDto1 = postService.update(id,postUpdateDto,user);
        return ResponseEntity.status(HttpStatus.OK).body(postUpdateDto1);
    }
    @PutMapping("/upvote/{id}")
    public ResponseEntity<?> upvote(Authentication authentication,
                                @PathVariable int id,
                                @RequestParam int vote){
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        PostUpvoteDto postUpvoteDto = postService.upvote(id,user,vote);
        return ResponseEntity.status(HttpStatus.OK).body(postUpvoteDto);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<?> getPost(@PathVariable int id,
                                     @RequestParam int page,
                                     @RequestParam(defaultValue = "latest") String sortType){
        PostDto postDto = postService.getPost(id,page,sortType);
        return ResponseEntity.status(HttpStatus.OK).body(postDto);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchPost(@RequestParam int page,
                                        @RequestParam(required = true) String keyword,
                                        @RequestParam(defaultValue = "latest") String sortType){
        List<PostShowDto> postShowDtoList = postService.searchPost(page,keyword,sortType);
        return ResponseEntity.status(HttpStatus.OK).body(postShowDtoList);
    }
}
