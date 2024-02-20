package com.nhat.project.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpvoteDto {
    private int id;
    private int vote;
    private CommentDto comment;
}
