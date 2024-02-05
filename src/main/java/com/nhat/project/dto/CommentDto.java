package com.nhat.project.dto;

import com.nhat.project.entity.User;
import com.nhat.project.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private String content;

    private User user;

    private Post post;

}
