package com.nhat.project.exception;

public class NotValidOperationException extends RuntimeException{
    public NotValidOperationException(String message){
        super(message);
    }
}
