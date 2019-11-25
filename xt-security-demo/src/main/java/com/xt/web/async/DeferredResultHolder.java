package com.xt.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xt
 * @create 2019/11/25 12:05
 * @Desc
 */
@Component
@Data
public class DeferredResultHolder {
    private Map<String, DeferredResult<String>> map = new HashMap<>();
}

