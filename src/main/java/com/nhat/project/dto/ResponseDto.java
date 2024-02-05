package com.nhat.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private HttpStatus statusCode;

    private Object data;

}
