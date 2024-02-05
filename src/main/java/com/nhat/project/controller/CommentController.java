package com.nhat.project.controller;

import com.nhat.project.dto.CommentDto;
import com.nhat.project.dto.ResponseDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.User;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.NotValidOperationException;
import com.nhat.project.service.CommentService;
import com.nhat.project.service.PostService;
import com.nhat.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping("/reply")
    public ResponseEntity<?> reply(String content, Authentication authentication, int post_id) {
        String username = authentication.getName();
        commentService.reply(content, userService.findByUsername(username),postService.findById(post_id));
        return ResponseEntity.status(HttpStatus.OK).body(
                new CommentDto(content,userService.findByUsername(username),postService.findById(post_id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(Authentication authentication, @PathVariable int id){
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Post post = comment.getPost();
        try{
            commentService.delete(id,userService.findByUsername(username));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new CommentDto(comment.getContent(),user,post)
            );
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(Authentication authentication,
                                  @PathVariable int id,
                                  @RequestParam String content){
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Post post = comment.getPost();
        try{
            commentService.edit(id,user,content);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new CommentDto(content,user,post)
            );
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
    }
    @PutMapping("/upvote-comment/{id}")
    public ResponseEntity<?> upvoteComment(Authentication authentication,
                                           @PathVariable int id,
                                           @PathVariable int vote){
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Post post = comment.getPost();
        try{
            commentService.upvote(id,user,vote);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new CommentDto(comment.getContent(), user, post)
            );
        }
        catch (NotValidOperationException nvoe){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(nvoe);
        }
    }
}
