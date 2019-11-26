package com.xt.security.core.validate.code.generator;

import com.xt.security.core.validate.code.bean.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成校验码
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
