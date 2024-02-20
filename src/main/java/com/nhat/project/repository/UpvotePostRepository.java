package com.nhat.project.repository;

import com.nhat.project.entity.UpvotePost;
import com.nhat.project.entity.id.UpvotePostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UpvotePostRepository extends JpaRepository<UpvotePost, UpvotePostId> {
    @Query(value = "SELECT u FROM UpvotePost u WHERE u.user_id = :user_id AND u.post_id = :post_id",  nativeQuery = true)
    UpvotePost findByUpvotePostId(@Param("user_id") int user_id,
                                  @Param("post") int post);

}
