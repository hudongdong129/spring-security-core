package com.hu.security.core.validate.code.image;

import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.context.request.ServletWebRequest;

import com.hu.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * 图片验证码处理器
 * @author Administrator
 *
 */
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	
	/**
	 *  发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		// TODO Auto-generated method stub
		ImageIO.write(imageCode.getImage(),"JPEG", request.getResponse().getOutputStream());
	}



}
