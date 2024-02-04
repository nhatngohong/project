package com.nhat.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private HttpStatusCode statusCode;
    private Object data;
}
