package com.xt.web.controller;

import com.xt.exception.UserNotExistException;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 2. 转发到/error进行自适应响应效果处理
     *
     * handleException：会处理所有 Controller 层抛出的 UserNotExistException 及其子类的异常
     *
     */
    @ExceptionHandler(UserNotExistException.class)
    public String handlerException(UserNotExistException ex, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("id", ex.getId());
        map.put("msg", ex.getMessage());
        request.setAttribute("ext", map);
        /**
         * 传入自己的错误状态码 4xx、5xx, 否则就不会进入定制错误页面的解析流程
         * Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);

        return "forward:/error";
    }
}
