package com.nhat.project.dto;

import com.nhat.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String title;

    private String content;

    private User user;

}
