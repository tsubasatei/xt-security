package com.xt.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

    private String loginPage = "/xt-signIn.html";

    private LoginType loginType = LoginType.JSON;

    private Integer rememberMeSeconds = 3600; // 记住我时间，单位：秒
}
