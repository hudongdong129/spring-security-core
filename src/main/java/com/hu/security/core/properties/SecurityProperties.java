/**
 * @author huDong
 */
package com.hu.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 *
 */
@ConfigurationProperties(prefix="hu.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}


	
}
