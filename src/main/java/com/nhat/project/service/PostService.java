package com.nhat.project.service;

import com.nhat.project.repository.CommentRepository;
import com.nhat.project.repository.PostRepository;
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
}
