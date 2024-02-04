package com.nhat.project.repository;

import com.nhat.project.entity.UpvoteComment;
import com.nhat.project.entity.id.UpvoteCommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpvoteCommentRepository extends JpaRepository<UpvoteComment, UpvoteCommentId> {
    UpvoteComment findByUpvoteCommentId(UpvoteCommentId upvoteCommentId);

}
