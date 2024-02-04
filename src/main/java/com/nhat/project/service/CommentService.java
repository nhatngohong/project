package com.nhat.project.service;

import com.nhat.project.entity.Post;
import com.nhat.project.entity.UpvoteComment;
import com.nhat.project.entity.id.UpvoteCommentId;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.User;
import com.nhat.project.exception.NotValidOperationException;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UpvoteCommentRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UpvoteCommentRepository upvoteCommentRepository;
    public void reply(String content, User owner, Post post){
        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setUpvote(0);
        newComment.setOwner(owner);
        newComment.setPost(post);
        commentRepository.save(newComment);
        //TODO fix createDate
    }
    public void delete(int id, User owner) {
        Comment comment = commentRepository.findById(id);
        if (comment.getOwner() == owner){
            commentRepository.deleteById(id);
        }
        else{
            throw new NotOwnerException("You can not edit this comment");
        }
        //TODO fix updateDate
    }
    public void edit(int id, User owner, String content) {
        Comment comment = commentRepository.findById(id);
        if (comment.getOwner() == owner){
            comment.setContent(content);
            commentRepository.save(comment);
        }
        else {
            throw new NotOwnerException("You can not edit this comment");
        }
    }
    public void upvote(int id, User user,int vote) {
        if (vote != 1 && vote != -1) {
            throw new NotValidOperationException("Vote must be 1 or -1 ");
        }
        UpvoteComment upvoteComment = upvoteCommentRepository.findByUpvoteCommentId(new UpvoteCommentId(user.getId(),id));
        Comment comment =  commentRepository.findById(id);
        if (upvoteComment == null){
            comment.setUpvote(comment.getUpvote() + vote);
            upvoteCommentRepository.save(new UpvoteComment(user,comment));
        }
        else {
            comment.setUpvote(comment.getUpvote() - vote);
            upvoteCommentRepository.delete(upvoteComment);
        }
    }
}
