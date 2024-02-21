package com.nhat.project.exception;

public class UsernameAlreadyTaken extends RuntimeException{
    public UsernameAlreadyTaken(String message){
        super(message);
    }
}
