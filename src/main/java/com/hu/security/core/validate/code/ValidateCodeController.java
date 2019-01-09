/**
 * @author huDong
 */
package com.hu.security.core.validate.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.hu.security.core.properties.SecurityConstants;
import com.hu.security.core.properties.SecurityProperties;
import com.hu.security.core.validate.code.image.ImageCode;
import com.hu.security.core.validate.code.sms.SmsCodeSender;



/**
 * @author Administrator
 *
 */
@RestController
public class ValidateCodeController {
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	/**
	 *  短信发送验证码
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request,HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
		
	}
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//
	/** 处理session */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//	
//	
//	@Autowired
//	private SecurityProperties securityProperties;
//	
//	@Autowired
//	private ValidateCodeGenerator imageCodeGenerator;
//	
//	@Autowired
//	private ValidateCodeGenerator smsCodeGenerator;
	
	/**
	 *  短信发送验证码
	 */
//	@Autowired
//	private SmsCodeSender smsCodeSender;
//	
//	@GetMapping("/code/image")
//	public void createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		ImageCode imageCode = (ImageCode)imageCodeGenerator.createImageCode(new ServletWebRequest(request));
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//	}
//	
//	@GetMapping("/code/sms")
//	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//		
//		ValidateCode smsCode = smsCodeGenerator.createImageCode(new ServletWebRequest(request));
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//		
//		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//		smsCodeSender.send(mobile, smsCode.getCode());
////		ImageIO.write(smsCode.getImage(), "JPEG", response.getOutputStream());
//	}


	
	
	
}
