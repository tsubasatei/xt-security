package com.xt.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
