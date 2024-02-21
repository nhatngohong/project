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
    private Integer owner;
    private Integer comment;
    @Override
    public int hashCode(){
        return Objects.hash(owner, comment);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpvoteCommentId upvoteCommentId = (UpvoteCommentId) o;
        return owner.equals(upvoteCommentId.owner) &&
                comment.equals(upvoteCommentId.comment);
    }
}
