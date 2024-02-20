package com.nhat.project.exception.not_found;

import com.nhat.project.entity.Post;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String message){
        super(message);
    }
}
