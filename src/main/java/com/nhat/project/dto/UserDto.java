package com.nhat.project.dto;

import com.nhat.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;

    private int contribution;

    private LocalDateTime createDate;

}
