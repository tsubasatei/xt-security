package com.xt.security.browser.support;


import lombok.Data;

@Data
public class SimpleResponse{

    private Object object;

    public SimpleResponse(Object object) {
        this.object = object;
    }

}
