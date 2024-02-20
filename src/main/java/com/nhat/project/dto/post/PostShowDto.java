package com.nhat.project.dto.post;

import com.nhat.project.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostShowDto {
    private String title;
    private String content;
    private UserDto owner;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
