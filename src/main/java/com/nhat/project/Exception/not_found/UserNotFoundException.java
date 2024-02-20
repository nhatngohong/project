package com.nhat.project.exception.not_found;

import com.nhat.project.entity.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
