package com.nhat.project.dto.post;

import com.nhat.project.dto.comment.CommentDto;
import com.nhat.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int id;

    private String title;

    private String content;

    private UserDto user;

    private int upvote;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private List<CommentDto> comments;

}
