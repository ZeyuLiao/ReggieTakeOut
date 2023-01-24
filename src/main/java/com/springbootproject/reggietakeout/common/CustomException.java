package com.springbootproject.reggietakeout.common;

public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
