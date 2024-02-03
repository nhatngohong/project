package com.nhat.project.repository;

import com.nhat.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findById(int id);
}
