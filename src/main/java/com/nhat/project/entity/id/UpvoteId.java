package com.nhat.project.entity.id;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpvoteId implements Serializable {
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
        UpvoteId upvoteId = (UpvoteId) o;
        return user_id.equals(upvoteId.user_id) &&
                post_id.equals(upvoteId.post_id);
    }
}