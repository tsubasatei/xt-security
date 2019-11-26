package com.xt.security.core.validate.code.sms.impl;

import com.xt.security.core.validate.code.sms.SmsCodeSender;

/**
 * 短信发送的默认实现
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机号：" + mobile + " 的用户，发送短信验证码：" + code);
    }
}
