package com.xt.security.core.validate.code.config;

import com.xt.security.core.validate.code.generator.ValidateCodeGenerator;
import com.xt.security.core.validate.code.image.impl.ImageCodeGenerator;
import com.xt.security.core.validate.code.sms.SmsCodeSender;
import com.xt.security.core.validate.code.sms.impl.DefaultSmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 校验码配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        return new ImageCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
