package com.nhat.project.entity;

import com.nhat.project.entity.id.UpvoteCommentId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upvote_comment")
@IdClass(UpvoteCommentId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpvoteComment {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Id
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private int vote;

}
