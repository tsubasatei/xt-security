package com.xt.security.core.properties;


import lombok.Data;

/**
 * 图片/短信相关验证码
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
