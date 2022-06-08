package com.ex2.catandtoy.exception;

public class CatCustomException extends Exception {
    public CatCustomException(ErrMsg errMsg){
        super(errMsg.getMessage());
    }
}
