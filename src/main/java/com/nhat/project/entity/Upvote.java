package com.nhat.project.entity;

import com.nhat.project.entity.id.UpvoteId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "upvote")
@IdClass(UpvoteId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Upvote {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToMany
    @JoinColumn(name = "post_id")
    private Post post;

    private int vote;

}
