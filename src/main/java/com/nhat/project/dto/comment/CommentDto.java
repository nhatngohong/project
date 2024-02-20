package com.nhat.project.dto.comment;

import com.nhat.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private int id;

    private int upvote;

    private String content;

    private UserDto user;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}
