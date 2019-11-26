package com.xt.security.browser;


import com.xt.security.core.authentication.AbstractChannelSecurityConfig;
import com.xt.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xt.security.core.properties.SecurityConstants;
import com.xt.security.core.properties.SecurityProperties;
import com.xt.security.core.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    /**
     * 系统封装配置类
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 获取用户登录信息
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 短信登录验证的配置
     */
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    /**
     * 验证码的配置
     */
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * TokenRepository --记住我
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true); // 启动时创建存放 token 的表
        return tokenRepository;
    }

    // 加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        /**
         * 任何方法都验证
         */
//        http.httpBasic()
        // 把 验证码过滤器加在 UsernamePasswordAuthenticationFilter 过滤器之前
        http
            .apply(validateCodeSecurityConfig)
                .and()
            .apply(smsCodeAuthenticationSecurityConfig)
                .and()
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .csrf().disable();
    }
}
