package com.nhat.project.exception;

public class NotOwnerException extends RuntimeException{
    public NotOwnerException(String message){
        super(message);
    }
}
