package com.nhat.project.controller;

import com.nhat.project.dto.comment.CommentDto;
import com.nhat.project.dto.comment.CommentUpvoteDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.User;
import com.nhat.project.exception.not_found.CommentNotFoundException;
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
@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @PostMapping("/reply/{id}")
    public ResponseEntity<?> reply(Authentication authentication,
                                   @RequestBody CommentDto commentDto,
                                   @PathVariable int id) {
        String username = authentication.getName();
        try{
            CommentDto comment = commentService.reply(commentDto.getContent(), userService.findByUsername(username), id);
            return ResponseEntity.status(HttpStatus.OK).body(comment);
        }
        catch (PostNotFoundException postNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postNotFoundException);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(Authentication authentication, @PathVariable int id){
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Post post = comment.getPost();
        try{
            CommentDto commentDto = commentService.delete(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(commentDto);
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
        catch (CommentNotFoundException commentNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commentNotFoundException);
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(Authentication authentication,
                                  @PathVariable int id,
                                  @RequestBody CommentDto commentUpdate){
        String content = commentUpdate.getContent();
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Post post = comment.getPost();
        try{
            CommentDto commentDto = commentService.edit(id,user,content);
            return ResponseEntity.status(HttpStatus.OK).body(commentDto);
        }
        catch (NotOwnerException noe){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(noe);
        }
        catch (CommentNotFoundException commentNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commentNotFoundException);
        }
    }
    @PutMapping("/upvote/{id}")
    public ResponseEntity<?> upvoteComment(Authentication authentication,
                                           @PathVariable int id,
                                           @RequestParam int vote){
        Comment comment = commentService.findById(id);
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        try{
            CommentUpvoteDto commentUpvoteDto = commentService.upvote(id,user,vote);
            return ResponseEntity.status(HttpStatus.OK).body(commentUpvoteDto);
        }
        catch (InvalidOperationException nvoe){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(nvoe);
        }
        catch (CommentNotFoundException commentNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commentNotFoundException);
        }
    }
}
