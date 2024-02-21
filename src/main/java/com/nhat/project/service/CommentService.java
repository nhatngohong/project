package com.nhat.project.service;

import com.nhat.project.dto.comment.CommentDto;
import com.nhat.project.dto.comment.CommentUpvoteDto;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.UpvoteComment;
import com.nhat.project.exception.not_found.CommentNotFoundException;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.User;
import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.not_found.PostNotFoundException;
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
    private static final int UPVOTE = 1;
    private static final int DOWNVOTE = -1;
    public CommentDto reply(String content, User owner, int id) {
        Comment newComment = new Comment();
        Post post = postRepository.findById(id);

        if (post == null) throw new PostNotFoundException("Post not found");

        newComment.setContent(content);
        newComment.setUpvote(0);
        newComment.setOwner(owner);
        newComment.setPost(post);
        Comment comment = commentRepository.save(newComment);
        return comment.convertToDto();
    }
    public CommentDto delete(int id, User owner)  {
        Comment comment = commentRepository.findById(id);

        if (comment == null) throw new CommentNotFoundException("Comment not found");

        if (comment.getOwner() == owner){
            commentRepository.deleteById(id);
            return comment.convertToDto();
        }
        else{
            throw new NotOwnerException("you can not delete this comment");
        }
    }
    public CommentDto edit(int id, User owner, String content) {
        Comment comment = commentRepository.findById(id);

        if (comment == null) throw new CommentNotFoundException("Comment not found");

        if (comment.getOwner() == owner){
            comment.setContent(content);
            Comment res = commentRepository.save(comment);
            return res.convertToDto();
        }
        else {
            throw new NotOwnerException("You can not edit this comment");
        }
    }
    public CommentUpvoteDto upvote(int id, User user, int vote) {
        if (vote != UPVOTE && vote != DOWNVOTE) {
            throw new InvalidOperationException("Vote must be UPVOTE or DOWNVOTE ");
        }

        if (commentRepository.findById(id) == null) throw new CommentNotFoundException("Comment not found");

        UpvoteComment upvoteComment = upvoteCommentRepository.findByUpvoteCommentId(user.getId(),id);
        Comment comment =  commentRepository.findById(id);
        if (upvoteComment == null){
            comment.setUpvote(comment.getUpvote() + vote);
            upvoteCommentRepository.save(new UpvoteComment(user,comment,vote));
        }
        else {
            if (upvoteComment.getVote() == vote){
                comment.setUpvote(comment.getUpvote() - vote);
                upvoteCommentRepository.delete(upvoteComment);
            }
            else {
                comment.setUpvote(comment.getUpvote() + 2 * vote);
                upvoteComment.setVote(-upvoteComment.getVote());
                upvoteCommentRepository.save(upvoteComment);
            }
        }
        return new CommentUpvoteDto(comment.getId(),vote,comment.convertToDto());
    }
    public Comment findById(int id){
        return commentRepository.findById(id);
    }
}
