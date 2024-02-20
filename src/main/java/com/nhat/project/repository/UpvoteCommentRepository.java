package com.nhat.project.repository;

import com.nhat.project.entity.UpvoteComment;
import com.nhat.project.entity.id.UpvoteCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UpvoteCommentRepository extends JpaRepository<UpvoteComment, UpvoteCommentId> {
    @Query(value = "SELECT u FROM UpvoteComment u WHERE u.user_id = :user_id AND u.comment_id = :comment_id",  nativeQuery = true)
    UpvoteComment findByUpvoteCommentId(@Param("user_id") int user_id,
                                        @Param("comment_id") int comment_id);

}
