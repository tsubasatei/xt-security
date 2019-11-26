package com.xt.security.core.validate.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 */
public interface ValidateCodeProcessor {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * 创建校验码
	 * 
	 * @param request : ServletWebRequest 封装请求和响应
	 * @throws Exception
	 */
	void createCode(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
