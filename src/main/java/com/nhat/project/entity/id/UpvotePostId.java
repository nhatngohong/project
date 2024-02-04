package com.nhat.project.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpvotePostId {
    private Integer user_id;
    private Integer post_id;
    @Override
    public int hashCode(){
        return Objects.hash(user_id,post_id);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpvotePostId upvotePostId = (UpvotePostId) o;
        return user_id.equals(upvotePostId.user_id) &&
                post_id.equals(upvotePostId.post_id);
    }
}
