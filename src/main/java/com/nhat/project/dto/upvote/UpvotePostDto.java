package com.nhat.project.dto.upvote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpvotePostDto {

    private String title;

    private String content;

    private String vote;
}
