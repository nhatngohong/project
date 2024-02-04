package com.nhat.project.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpvoteCommentId implements Serializable {
    private Integer user_id;
    private Integer comment_id;
    @Override
    public int hashCode(){
        return Objects.hash(user_id,comment_id);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpvoteCommentId upvoteCommentId = (UpvoteCommentId) o;
        return user_id.equals(upvoteCommentId.user_id) &&
                comment_id.equals(upvoteCommentId.comment_id);
    }
}
