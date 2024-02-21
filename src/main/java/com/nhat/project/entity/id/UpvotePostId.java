package com.nhat.project.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpvotePostId {
    private Integer owner;
    private Integer post;
    @Override
    public int hashCode(){
        return Objects.hash(owner, post);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpvotePostId upvotePostId = (UpvotePostId) o;
        return owner.equals(upvotePostId.owner) &&
                post.equals(upvotePostId.post);
    }
}
