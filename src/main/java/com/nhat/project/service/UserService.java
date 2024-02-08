package com.nhat.project.service;

import com.nhat.project.entity.Comment;
import com.nhat.project.entity.Post;
import com.nhat.project.entity.User;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<Post> showPost(int page, int id){
        Pageable pageable = PageRequest.of(page - 1,5);
        List<Post> posts = postRepository.findByUser_id(id,pageable, Sort.by("createDate"));
        return posts;
    }
    public List<Comment> showComment(int page,int id){
        Pageable pageable = PageRequest.of(page - 1,15);
        List<Comment> comments = commentRepository.findByUser_id(id,pageable);
        return comments;
    }
}
