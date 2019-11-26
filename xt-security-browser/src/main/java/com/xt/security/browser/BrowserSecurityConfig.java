package com.xt.security.browser;


import com.xt.security.browser.authentication.XtAuthenticationFailureHandler;
import com.xt.security.core.properties.SecurityProperties;
import com.xt.security.core.validate.code.ValidationCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler xtAuthenticationSuccessHandler;

    @Autowired
    private XtAuthenticationFailureHandler xtAuthenticationFailureHandler;

    // 加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidationCodeFilter validationCodeFilter = new ValidationCodeFilter();
        validationCodeFilter.setAuthenticationFailureHandler(xtAuthenticationFailureHandler);
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
