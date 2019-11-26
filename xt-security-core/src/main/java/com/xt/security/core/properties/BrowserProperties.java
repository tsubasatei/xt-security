package com.xt.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

    private String signUpUrl = "/xt-signUp.html";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginResponseType loginResponseType = LoginResponseType.JSON;

    private Integer rememberMeSeconds = 3600; // 记住我时间，单位：秒
}
