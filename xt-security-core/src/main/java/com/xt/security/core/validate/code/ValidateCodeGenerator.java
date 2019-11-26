package com.xt.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成校验码
 */
public interface ValidateCodeGenerator {

    /**
     * 生成图片校验码
     * @param request
     * @return
     */
    ImageCode generate(ServletWebRequest request);
}
