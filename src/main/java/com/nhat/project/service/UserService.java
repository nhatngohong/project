package com.nhat.project.service;

import com.nhat.project.dto.post.PostShowDto;
import com.nhat.project.dto.user.UserCreateDto;
import com.nhat.project.dto.user.UserDto;
import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.User;
import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.UsernameAlreadyTaken;
import com.nhat.project.exception.not_found.UserNotFoundException;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<Post> showPost(int page, int id){
        Pageable pageable = PageRequest.of(page ,5);
        List<Post> posts = postRepository.findByUserId(id,pageable);
        return posts;
    }
    public List<Comment> showComment(int page,int id){
        Pageable pageable = PageRequest.of(page,15);
        List<Comment> comments = commentRepository.findByUser_id(id,pageable);
        return comments;
    }
    public UserDto getInfo(String username, int id) throws UserNotFoundException{
        User user = userRepository.findById(id);
        if (user == null){
            throw new UserNotFoundException("User not found");
        }
        return new UserDto(user.getId(),username,user.getContribution(),user.getCreateDate());
    }
    public List<PostShowDto> getUserPost(int id, int page, String sortType) throws InvalidOperationException {
        Pageable pageable = PageRequest.of(page,10);
        List<Post> posts = new ArrayList<>();
        List<PostShowDto> postShowDtos = new ArrayList<>();
        if (Objects.equals(sortType, "latest")) posts = postRepository.findAllByDate(pageable);
        else if (Objects.equals(sortType, "upvote")) posts = postRepository.findAllByUpvote(pageable);
        else throw new InvalidOperationException("Invalid sort type");
        for (Post post:posts){
            postShowDtos.add(new PostShowDto(post.getTitle(),post.getContent(),post.getUpvote(),post.getOwner().convertToDto(),post.getCreateDate(),post.getUpdateDate()));
        }
        return postShowDtos;
    }
    public void register(UserCreateDto userCreateDto) throws UsernameAlreadyTaken {
        if (userRepository.findByUsername(userCreateDto.getUsername()) != null) throw new UsernameAlreadyTaken("Username already taken");
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setRoles("USER");
        user.setContribution(0);
        userRepository.save(user);
    }
}
