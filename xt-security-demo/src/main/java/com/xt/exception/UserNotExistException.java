package com.xt.exception;

import lombok.Data;

@Data
public class UserNotExistException extends RuntimeException{

    private Integer id;
    public UserNotExistException(Integer id) {
        super("user not exist");
        this.id = id;
    }
}
