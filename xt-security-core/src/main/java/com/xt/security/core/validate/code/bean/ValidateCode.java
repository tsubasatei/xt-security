package com.xt.security.core.validate.code.bean;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码基础类
 */
@Data
public class ValidateCode {
    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    /**
     *
     * @param code
     * @param expireIn：有效期，单位：分钟
     */
    public ValidateCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 是否过期
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
