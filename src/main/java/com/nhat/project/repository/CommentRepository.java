package com.nhat.project.repository;

import com.nhat.project.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findById(int id);
    @Query("SELECT c FROM Comment c WHERE c.post.id = :id ORDER BY c.upvote DESC")
    List<Comment> findByPostSortByUpvote(@Param("id") int id, Pageable pageable);
    @Query(value = "SELECT c FROM Comment c WHERE c.post.id = :id ORDER BY c.createDate DESC")
    List<Comment> findByPostSortByDate(@Param("id") int id, Pageable pageable);
    @Query(value = "SELECT c FROM Comment c WHERE c.owner.id = :id ORDER BY c.createDate DESC")
    List<Comment> findByUser_id(@Param("id") int id, Pageable pageable);
}
