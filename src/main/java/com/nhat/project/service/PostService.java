package com.nhat.project.service;

import com.nhat.project.entity.Post;
import com.nhat.project.entity.UpvotePost;
import com.nhat.project.entity.User;
import com.nhat.project.entity.id.UpvotePostId;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.NotValidOperationException;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UpvotePostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UpvotePostRepository upvotePostRepository;

    public void newPost(String content, String title, User user){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setOwner(user);
        post.setVote(0);
        postRepository.save(post);
        //TODO fix createDate
    }

    public void update(int id, String content, User user){
        Post post = postRepository.findById(id);
        if (post.getOwner() == user){
            post.setContent(content);
            postRepository.save(post);
        }
        else {
            throw new NotOwnerException("You can not edit thus post");
        }
        //TODO fix updateDate
    }
    public void delete(int id, User user){
        Post post = postRepository.findById(id);
        if (post.getOwner() == user){
            postRepository.delete(post);
        }
        else{
            throw new NotOwnerException("You can not delete this post");
        }
    }
    public void upvote(int id, User user, int vote){
        if (vote != 1 && vote != -1) {
            throw new NotValidOperationException("Vote must be 1 or -1 ");
        }
        UpvotePost upvotePost = upvotePostRepository.findByUpvotePostId(new UpvotePostId(user.getId(), id));
        Post post = postRepository.findById(id);
        if (upvotePost == null) {
            post.setVote(post.getVote() + vote);
            upvotePostRepository.save(new UpvotePost(user,post));
        }
        else {
            post.setVote(post.getVote() - vote);
            upvotePostRepository.delete(upvotePost);
        }
    }
}
