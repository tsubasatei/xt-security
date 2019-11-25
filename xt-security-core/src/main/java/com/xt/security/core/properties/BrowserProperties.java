package com.xt.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {

    private String loginPage = "/xt-signIn.html";

    private LoginType loginType = LoginType.JSON;
}
