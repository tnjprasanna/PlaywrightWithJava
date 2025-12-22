package com.qa.opencart.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {
	
	
	
	private Page page;
	
	private String email = "input#input-email";
	private String password = "input#input-password";
	private String loginButton = "input[value='Login']";
	private String forgotPassword = "//div[@class='form-group']//a";
	private String myAccountText = "#content h2:first-of-type";
	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	public String getLoginPageTitle() {
		return page.title();
	}
	
	public boolean isForgotPwdDisp() {
		return page.isVisible(forgotPassword);
	}
	
	public boolean doLogin(String appUserName, String appPassword) {
		System.out.println("App Cred: "+ appUserName +" : "+appPassword);
		page.fill(email, appUserName);
		page.fill(password, appPassword);
		page.click(loginButton);
		if(page.isVisible(myAccountText)) {
			System.out.println("login successfully");
			return true;
		} return false;
	}
	
	
}
