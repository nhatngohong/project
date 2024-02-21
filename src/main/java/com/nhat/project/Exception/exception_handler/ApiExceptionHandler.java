package com.nhat.project.exception.exception_handler;

import com.nhat.project.exception.InvalidOperationException;
import com.nhat.project.exception.NotOwnerException;
import com.nhat.project.exception.UsernameAlreadyTaken;
import com.nhat.project.exception.not_found.CommentNotFoundException;
import com.nhat.project.exception.not_found.PostNotFoundException;
import com.nhat.project.exception.not_found.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ApiExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({NotOwnerException.class})
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage forbidden(Exception e){
        logger.error(e.toString());
        return new ErrorMessage(403,e.getMessage());
    }
    @ExceptionHandler({InvalidOperationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequest(Exception e){
        logger.error(e.toString());
        return new ErrorMessage(400,e.getMessage());
    }
    @ExceptionHandler({CommentNotFoundException.class, PostNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notFound(Exception e){
        logger.error(e.toString());
        return new ErrorMessage(404,e.getMessage());
    }
    @ExceptionHandler({UsernameAlreadyTaken.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage usernameExist(Exception e){
        logger.error(e.toString());
        return new ErrorMessage(409,e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ErrorMessage errorMessage(Exception e){
        logger.error(e.toString());
        return new ErrorMessage(520,"Unknown error");
    }
}
