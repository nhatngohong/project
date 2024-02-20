package com.nhat.project.dto.post;

import com.nhat.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDeleteDto {
    private String title;
    private String content;
    private UserDto user;
}
