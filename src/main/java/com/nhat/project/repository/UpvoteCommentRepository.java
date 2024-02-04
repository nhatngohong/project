package com.nhat.project.repository;

import com.nhat.project.entity.UpvoteComment;
import com.nhat.project.entity.id.UpvoteCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UpvoteCommentRepository extends JpaRepository<UpvoteComment, UpvoteCommentId> {
    UpvoteComment findByUpvoteCommentId(UpvoteCommentId upvoteCommentId);

}
