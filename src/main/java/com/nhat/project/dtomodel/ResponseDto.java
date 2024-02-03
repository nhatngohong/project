package com.nhat.project.dtomodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private HttpStatusCode statusCode;
    private Object data;
}
