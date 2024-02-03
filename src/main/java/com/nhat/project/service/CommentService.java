package com.nhat.project.service;

import com.nhat.project.Exception.NotOwnerException;
import com.nhat.project.dtomodel.UserDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.User;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    public void reply(String content, User owner){
        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setUpvote(0);
        newComment.setOwner(owner);
        commentRepository.save(newComment);
    }
    public void delete(int id, User owner) throws NotOwnerException{
        Comment comment = commentRepository.findById(id);
        if (comment.getOwner() == owner){
            commentRepository.deleteById(id);
        }
        else
        //TODO fix
    }
    public void edit(int id, User owner, String content) throws NotOwnerException {
        Comment comment = commentRepository.findById(id);
        if (comment.getOwner() == owner){
            comment.setContent(content);
            commentRepository.save(comment);
        }
        else {
            throw NotOwnerException;
        }
        //TODO fix
    }
    public void upvote(int id, User user) throws NotOwnerException{
        Comment comment = commentRepository.findById(id);

    }
}
