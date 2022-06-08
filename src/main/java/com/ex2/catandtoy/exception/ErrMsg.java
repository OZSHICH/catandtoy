package com.ex2.catandtoy.exception;

import lombok.*;

@Getter
public enum ErrMsg {
    CAT_IS_DEAD("Cat is dead"),
    CAT_ALREADY_EXIST("Cat already exists"),
    CAT_NOT_EXIST("Cat not exists"),
    TOY_ALREADY_EXIST("Toy already exists"),
    TOY_NOT_EXIST("Toy not exists");

    private String Message;

    ErrMsg(String message) {
        Message = message;
    }
}
