package com.nhat.project.repository;

import com.nhat.project.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findById(int id);
    @Query("SELECT p FROM Post p ORDER BY p.upvote DESC")
    List<Post> findAllByUpvote(Pageable pageable);
    @Query("SELECT p FROM Post p ORDER BY p.createDate DESC")
    List<Post> findAllByDate(Pageable pageable);
    @Query(value = "SELECT p FROM Post p WHERE p.user_id = :id ORDER BY p.createDate DESC", nativeQuery = true)
    List<Post> findByUser_id(@Param("id") int id, Pageable pageable);
}
