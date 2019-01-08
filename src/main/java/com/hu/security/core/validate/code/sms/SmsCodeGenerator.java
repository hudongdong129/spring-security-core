/**
 * @author huDong
 */
package com.hu.security.core.validate.code.sms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.hu.security.core.properties.SecurityProperties;
import com.hu.security.core.validate.code.ValidateCode;
import com.hu.security.core.validate.code.ValidateCodeGenerator;

/**
 * @author Administrator
 *
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
	/* (non-Javadoc)
	 * @see com.hu.security.core.validate.code.ValidateCodeGenerator#createImageCode(org.springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode createImageCode(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
	
	

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	
}
