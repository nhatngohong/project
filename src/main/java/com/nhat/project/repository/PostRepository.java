package com.nhat.project.repository;

import com.nhat.project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface PostRepository extends JpaRepository<Post,Integer> {
}
