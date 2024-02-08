package com.nhat.project.controller;

import com.nhat.project.dto.PostDto;
import com.nhat.project.dto.UserDto;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.User;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.NotValidOperationException;
import com.nhat.project.exception.PostNotFoundException;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping("/ask")
    public ResponseEntity<?> ask(Authentication authentication,
                              @RequestBody PostDto postDto){
        String content = postDto.getContent();
        String title = postDto.getTitle();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        postService.ask(content,title,user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PostDto(title,content,user.convertToDto(),null)
        );
    }
    @DeleteMapping("/delete-post/{id}")
    private ResponseEntity<?> deletePost(Authentication authentication,
                                              @PathVariable int id){
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        try{
            postService.delete(id,user);
            return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
            //TODO fix
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
    }
    @PutMapping("/update-post/{id}")
    private ResponseEntity<?> updatePost(Authentication authentication,
                                              @PathVariable int id,
                                              @RequestParam String content){

        String username = authentication.getName();
        User user = userService.findByUsername(username);
        try{
            postService.update(id,content,user);
            return ResponseEntity.status(HttpStatus.OK).body("Update successfully");
            //TODO fix
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
    }
//    @PutMapping("/upvote-post/{id}")
//    public ResponseEntity<?> upvoteComment(Authentication authentication,
//                                @PathVariable int id,
//                                @PathVariable int vote){
//        String username = authentication.getName();
//        User user = userService.findByUsername(username);
//        try{
//            postService.upvote(id,user,vote);
//            return ResponseEntity.status(HttpStatus.OK).body("upvote successfully");
//            //TODO fix
//        }
//        catch (NotValidOperationException nvoe){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(nvoe);
//        }
//    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable int id){
        try{
            Post post = postService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(post);
            //TODO fix
        }catch (PostNotFoundException pnfe){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pnfe);
        }
    }
}
