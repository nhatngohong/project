package com.nhat.project.repository;

import com.nhat.project.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findById(int id);
    @Query("SELECT p FROM Post p ORDER BY p.upvote DESC")
    List<Post> findAllByUpvote(Pageable pageable);
    @Query("SELECT p FROM Post p ORDER BY p.createDate DESC")
    List<Post> findAllByDate(Pageable pageable);
    @Query("SELECT p FROM Post p WHERE p.owner.id = :id ORDER BY p.createDate DESC")
    List<Post> findByUserId(int id, Pageable pageable);
    @Query("SELECT p FROM Post p " +
            "WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%" +
            " ORDER BY p.createDate DESC")
    List<Post> searchPostSortByDate(String keyword, Pageable pageable);
    @Query("SELECT p FROM Post p " +
            "WHERE p.title LIKE %:keyword% OR p.content LIKE %:keyword%" +
            " ORDER BY p.upvote DESC")
    List<Post> searchPostSortByUpvote(String keyword, Pageable pageable);
}
