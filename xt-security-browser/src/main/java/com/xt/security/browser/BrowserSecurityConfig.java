package com.xt.security.browser;


import com.xt.security.browser.authentication.XtAuthenticationFailureHandler;
import com.xt.security.core.properties.SecurityProperties;
import com.xt.security.core.validate.code.ValidationCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    // 系统封装配置类
    @Autowired
    private SecurityProperties securityProperties;

    // 登录成功处理器
    @Autowired
    private AuthenticationSuccessHandler xtAuthenticationSuccessHandler;

    // 登录失败处理器
    @Autowired
    private AuthenticationFailureHandler xtAuthenticationFailureHandler;

    // 数据源
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    // TokenRepository --记住我
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

        ValidationCodeFilter validationCodeFilter = new ValidationCodeFilter();
        validationCodeFilter.setAuthenticationFailureHandler(xtAuthenticationFailureHandler);
        validationCodeFilter.setSecurityProperties(securityProperties);
        validationCodeFilter.afterPropertiesSet();
        /**
         * 任何方法都验证
         */
//        http.httpBasic()
        // 把 验证码过滤器加在 UsernamePasswordAuthenticationFilter 过滤器之前
        http.addFilterBefore(validationCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(xtAuthenticationSuccessHandler)
                    .failureHandler(xtAuthenticationFailureHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                    .antMatchers( "/authentication/require",
                            securityProperties.getBrowser().getLoginPage(),
                            "/code/image").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf().disable();
    }
}
