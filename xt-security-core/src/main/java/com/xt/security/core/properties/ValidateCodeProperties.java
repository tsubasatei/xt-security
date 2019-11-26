package com.xt.security.core.properties;


import lombok.Data;

/**
 * 登录相关验证码
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();
}
