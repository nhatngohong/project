package com.nhat.project.service;

import com.nhat.project.dto.comment.CommentDto;
import com.nhat.project.dto.post.*;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.UpvotePost;
import com.nhat.project.entity.User;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.not_found.PostNotFoundException;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UpvotePostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    }

    public PostUpdateDto update(int id, PostCreateDto postUpdateDto, User user) throws NotOwnerException, PostNotFoundException{
        Post post = postRepository.findById(id);
        if (post == null) throw new PostNotFoundException("Post not found");
        if (post.getOwner() == user){
            String oldTitle = post.getTitle();
            String oldContent = post.getContent();
            post.setTitle(postUpdateDto.getTitle());
            post.setContent(postUpdateDto.getContent());
            postRepository.save(post);
            return new PostUpdateDto(id,oldTitle,post.getTitle(),oldContent,post.getContent(),user.convertToDto());
        }
        else {
            throw new NotOwnerException("You can not edit thus post");
        }
    }
    public PostDeleteDto delete(int id, User user) throws NotOwnerException, PostNotFoundException{
        Post post = postRepository.findById(id);

        if (post == null) throw new PostNotFoundException("Post not found");

        if (post.getOwner() == user){
            postRepository.delete(post);
            return new PostDeleteDto(post.getTitle(), post.getContent(), user.convertToDto());
        }
        else{
            throw new NotOwnerException("You can not delete this post");
        }
    }
    public PostUpvoteDto upvote(int id, User user, int vote) throws InvalidOperationException, PostNotFoundException{
        if (vote != UPVOTE && vote != DOWNVOTE) {
            throw new InvalidOperationException("Vote must be UPVOTE or DOWNVOTE ");
        }

        if (postRepository.findById(id) == null) throw new PostNotFoundException("Post not found");

        UpvotePost upvotePost = upvotePostRepository.findByUpvotePostId(user.getId(), id);
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
        return new PostUpvoteDto(id,vote,post.convertToDto());
    }
    public PostDto getPost(int id, int page, String sortType) throws PostNotFoundException, InvalidOperationException {
        Post post = postRepository.findById(id);

        if (post == null) throw new PostNotFoundException("Post not found");

        if ((!Objects.equals(sortType, "latest")) && (!Objects.equals(sortType, "upvote"))) throw new InvalidOperationException("Invalid sort type");

        Pageable pageable = PageRequest.of(page,15);
        List<Comment> comments;
        if (sortType.equals("latest")) comments = commentRepository.findByPostSortByDate(id,pageable);
        else comments = commentRepository.findByPostSortByUpvote(id,pageable);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment:comments){
            System.out.println(comment.getContent());
            commentDtos.add(comment.convertToDto());
        }

        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getOwner().convertToDto(),
                post.getUpvote(),
                post.getCreateDate(),
                post.getUpdateDate(),
                commentDtos);
    }
    public List<PostShowDto> showPost(int page){
        Pageable pageable = PageRequest.of(page,5);
        List<PostShowDto> postShowDtos = new ArrayList<PostShowDto>();
        List<Post> posts = postRepository.findAllByDate(pageable);
        for (Post post:posts){
            postShowDtos.add(new PostShowDto(post.getTitle(),post.getContent(),post.getUpvote(),post.getOwner().convertToDto(),post.getCreateDate(),post.getUpdateDate()));
        }
        return postShowDtos;
    }
    public List<PostShowDto> findPost(int page,String keyword,String sortType) throws InvalidOperationException{

        if (!Objects.equals(sortType, "latest") && !Objects.equals(sortType, "upvote")) throw new InvalidOperationException("Invalid sort type");

        Pageable pageable = PageRequest.of(page,5);
        List<PostShowDto> postShowDtos = new ArrayList<PostShowDto>();
        List<Post> posts;
        if (sortType == "latest") posts = postRepository.searchPostSortByDate(keyword,pageable);
        else posts = postRepository.searchPostSortByUpvote(keyword,pageable);
        for (Post post:posts){
            postShowDtos.add(new PostShowDto(post.getTitle(),post.getContent(),post.getUpvote(),post.getOwner().convertToDto(),post.getCreateDate(),post.getUpdateDate()));
        }
        return postShowDtos;
    }
}
