package com.nhat.project.repository;

import com.nhat.project.entity.UpvotePost;
import com.nhat.project.entity.id.UpvotePostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UpvotePostRepository extends JpaRepository<UpvotePost, UpvotePostId> {
    UpvotePost findByUpvotePostId(UpvotePostId upvotePostId);

}
