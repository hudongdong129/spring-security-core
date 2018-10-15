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
