package com.nhat.project.dto.post;

import com.nhat.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto {
    private int id;
    private String oldTitle;
    private String newTitle;
    private String oldContent;
    private String newContent;
    private UserDto user;
}
