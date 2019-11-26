package com.xt.security.core.properties;

import lombok.Data;

/**
 * 短信验证码配置
 */
@Data
public class SmsCodeProperties {

    private Integer length = 6;
    private Integer expireIn = 60;
    private String url;

}
