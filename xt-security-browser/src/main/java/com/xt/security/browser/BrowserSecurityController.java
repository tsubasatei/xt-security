package com.xt.security.browser;

import com.xt.security.browser.support.SimpleResponse;
import com.xt.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 把当前的请求信息缓存到 RequestCache
    private RequestCache requestCache = new HttpSessionRequestCache();

    // 跳转工具
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String target = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求是: " + savedRequest);
            if (StringUtils.endsWithIgnoreCase(target, ".html")) {
                redirectStrategy.sendRedirect(request, response,  securityProperties.getBrowser().getLoginPage());
            }

        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
