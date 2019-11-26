package com.xt.security.core.properties;

import lombok.Data;

/**
 * 图片验证码配置
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private Integer length = 4;
    private Integer expireIn = 60;
    private String url;
}
