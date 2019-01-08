package com.hu.security.core.validate.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hu.security.core.properties.SecurityConstants;
import com.hu.security.core.properties.SecurityProperties;
import com.hu.security.core.validate.code.image.ImageCode;

/**
 * 
 * @author Administrator
 * OncePerRequestFilter ——保证只会调用一次
 * InitializingBean 保证其他参数配置完了后，在处理当前参数
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
//	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//	
//	private Set<String> urls = new HashSet<>();
	
	/**
	 *  系统配置信息
	 */
	private SecurityProperties securityProperties;
	
	/**
	 * 验证请求url与配置的url是否匹配的工具类
	 */
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	/**
	 * 存放所有需要校验验证码的url
	 */
	private Map<String, ValidateCodeType> urlMap = new HashMap<>();
	
	
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		
		urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FROM, ValidateCodeType.IMAGE);
		addUrlToMap(securityProperties.getCode().getImage().getUrl(),ValidateCodeType.IMAGE);
		
		urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,ValidateCodeType.SMS);
		addUrlToMap(securityProperties.getCode().getSms().getUrl(),ValidateCodeType.SMS);
//		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl(), ",");
//		for(String configUrl :configUrls) {
//			urls.add(configUrl);
//		}
//		urls.add("/authentication/form");
	}

	/**
	 * 将系统中配置的需要校验验证码的url根据校验的类型放入map
	 * @param url
	 * @param sms
	 */
	private void addUrlToMap(String urlString, ValidateCodeType type) {
		if (StringUtils.isNotBlank(urlString)) {
			String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
			for(String url :urls) {
				urlMap.put(url, type);	
			}
		}	
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		ValidateCodeType type = getValidateCodeType(request);
		
		if (type != null) {
			logger.info("校验请求("+ request.getRequestURI() + ")中的验证码，验证码类型" + type);
			try {
				validateCodeProcessorHolder.findValidateCodeProcessor(type)
					.validate(new ServletWebRequest(request,response));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
		
//		boolean action = false;
//		for(String url : urls) {
//			if (pathMatcher.match(url, request.getRequestURI())) {
//				action = true;
//			}
//		}
//		
//		if (action) {
//			try {
//				validate(new ServletWebRequest(request));
//			} catch (ValidateCodeException e) {
//				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//				return;
//			}
//		} 
//		
//		filterChain.doFilter(request, response);
	}

//	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//		ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
//		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
//		
//		if (StringUtils.isBlank(codeInRequest)) {
//			throw new ValidateCodeException("验证码的值不能为空");
//		}
//		if (codeInSession == null) {
//			throw new ValidateCodeException("验证码不存在");
//		}
//		if (codeInSession.isExpried()) {
//			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
//			throw new ValidateCodeException("验证码已过期");
//		}
//		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
//			throw new ValidateCodeException("验证码不匹配");	
//		}
//		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
//	}

	/**
	 *  获取校验码的类型，如果当前请求不需要校验，则返回null
	 * @param request
	 * @return
	 */
	private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		ValidateCodeType result = null;
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
			Set<String> urls = urlMap.keySet();
			for(String url : urls) {
				if (pathMatcher.match(url, request.getRequestURI())) {
					result = urlMap.get(url);			
				}
			}
		}
		return result;
	}
	
	
	

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	

//	public Set<String> getUrls() {
//		return urls;
//	}
//
//	public void setUrls(Set<String> urls) {
//		this.urls = urls;
//	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

	
}
