package com.xt.config;

import com.xt.web.interceptor.TimeInterceptor;
import com.xt.web.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * 1. 过滤器（Filter）：可以拦截到原始的 http请求和响应信息，但是拦截不到请求的方法信息
 *
 * 2. 拦截器（Interceptor）：可以拦截到原始的 http请求和响应信息，及处理请求的方法信息，但是拿不到参数值
 *
 * 3. 切片（Aspect）：可以拿到参数值，但是拿不到原始的 http 请求对象
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new TimeFilter());
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeInterceptor());
    }
}
