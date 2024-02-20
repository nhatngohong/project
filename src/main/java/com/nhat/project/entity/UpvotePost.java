package com.nhat.project.entity;

import com.nhat.project.entity.id.UpvotePostId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upvote_post")
@IdClass(UpvotePostId.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpvotePost {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post_id;

    private int vote;
}
