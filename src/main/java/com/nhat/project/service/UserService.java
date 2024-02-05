package com.nhat.project.service;

import com.nhat.project.entity.User;
import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
import com.nhat.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
