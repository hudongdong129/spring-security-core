/**
 * @author huDong
 */
package com.hu.security.core.properties;

/**
 * @author Administrator
 *
 */
public class BrowserProperties {

	private String loginPage = "/imooc-signIn.html";

	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;
	
	
	
	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}
	
	
	
}
