package com.xt.code;

import com.xt.security.core.validate.code.ImageCode;
import com.xt.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author xt
 * @create 2019/11/26 11:22
 * @Desc
 */
@Component(value = "imageCodeGenerator")
public class DemoValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
