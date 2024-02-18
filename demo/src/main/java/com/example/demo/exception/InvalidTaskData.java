package com.example.demo.exception;

public class InvalidTaskData extends  RuntimeException{

    public InvalidTaskData(String message) {
        super(message);
    }

    public InvalidTaskData(String message, Throwable cause) {
        super(message, cause);
    }
}
