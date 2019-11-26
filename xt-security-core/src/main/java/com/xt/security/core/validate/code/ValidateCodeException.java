package com.xt.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xt
 * @create 2019/11/25 22:14
 * @Desc
 */
public class ValidateCodeException extends AuthenticationException {


    public ValidateCodeException(String msg) {
        super(msg);
    }
}
