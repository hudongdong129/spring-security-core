/**
 * @author huDong
 */
package com.hu.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Administrator
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode createImageCode(ServletWebRequest request);
}
