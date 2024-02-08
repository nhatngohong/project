package com.nhat.project.dto;

import com.nhat.project.entity.Comment;
import com.nhat.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String title;

    private String content;

    private UserDto user;

    private List<CommentDto> comments;

}
