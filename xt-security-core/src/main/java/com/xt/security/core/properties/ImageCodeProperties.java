package com.xt.security.core.properties;

import lombok.Data;

/**
 * 图片验证码配置
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

}
