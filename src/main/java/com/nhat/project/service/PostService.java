package com.nhat.project.service;

import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.UpvotePost;
import com.nhat.project.entity.User;
import com.nhat.project.entity.id.UpvotePostId;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.NotValidOperationException;
import com.nhat.project.exception.PostNotFoundException;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UpvotePostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private static final int UPVOTE = 1;
    private static final int DOWNVOTE = -1;
    public void ask(String content, String title, User user){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setOwner(user);
        post.setUpvote(0);
        postRepository.save(post);
        //TODO fix createDate
    }

    public void update(int id, String content, User user) throws NotOwnerException{
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
    public void delete(int id, User user) throws NotOwnerException{
        Post post = postRepository.findById(id);
        if (post.getOwner() == user){
            postRepository.delete(post);
        }
        else{
            throw new NotOwnerException("You can not delete this post");
        }
    }
    public void upvote(int id, User user, int vote) throws NotValidOperationException{
        if (vote != UPVOTE && vote != DOWNVOTE) {
            throw new NotValidOperationException("Vote must be UPVOTE or DOWNVOTE ");
        }
        UpvotePost upvotePost = upvotePostRepository.findByUpvotePostId(new UpvotePostId(user.getId(), id));
        Post post = postRepository.findById(id);
        if (upvotePost == null) {
            post.setUpvote(post.getUpvote() + vote);
            upvotePostRepository.save(new UpvotePost(user,post,vote));
        }
        else {
            if (upvotePost.getVote() == vote){
                post.setUpvote(post.getUpvote() - vote);
                upvotePostRepository.delete(upvotePost);
            }
            else {
                post.setUpvote(post.getUpvote() + 2 * vote);
                upvotePost.setVote(-upvotePost.getVote());
                upvotePostRepository.save(upvotePost);
            }
        }
    }
    public List<Post> showPostByUpvote(int page){
        return postRepository.findAllByUpvote(PageRequest.of(page - 1, 5));
    }
    public List<Post> showPostByDate(int page){
        return postRepository.findAllByDate(PageRequest.of(page - 1, 5));
    }
    public Post findById(int id) throws PostNotFoundException {
        Post post = postRepository.findById(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        return postRepository.findById(id);
    }

    public List<Comment> showComment(int id){
        List<Comment> comments = commentRepository.findByPost(id);
        return comments;
    }
}
