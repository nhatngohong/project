package com.nhat.project.dto.upvote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpvoteCommentDto {

    private String content;

    private int vote;
}
