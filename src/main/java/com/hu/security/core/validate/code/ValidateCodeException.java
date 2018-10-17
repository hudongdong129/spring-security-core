/**
 * @author huDong
 */
package com.hu.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidateCodeException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
