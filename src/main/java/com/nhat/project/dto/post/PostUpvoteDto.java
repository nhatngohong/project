package com.nhat.project.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpvoteDto {
    private int id;
    private int vote;
    private PostDto post;
}
