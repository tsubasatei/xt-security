package com.xt.security.core.validate.code.image;

import com.xt.security.core.validate.code.bean.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
@Data
public class ImageCode extends ValidateCode {
    /**
     * 图片
     */
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, Integer expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
