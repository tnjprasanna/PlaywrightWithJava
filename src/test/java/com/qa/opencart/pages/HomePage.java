package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	Page page;
	
	private String search = "input[name='search']";
	private String searchButton = "div#search button";
	private String searchPageHeader = "div#content h1";
	private String loginOption = "a:text('Login')";
	private String myAccMenu = "a[title='My Account']";
	
	public HomePage(Page page) {
		this.page = page;	
	}
	
	public String getHomePageTitle() {
		String title = page.title();
		System.out.println("Page Title: "+title);
		return title;
	}
	
	public String getHomePageURL() {
		String url = page.url();
		System.out.println("Page url: "+url);
		return url;
	}
	
	public String doSearch(String productName) {
		page.fill(search, productName);
		page.click(searchButton);
		String header = page.textContent(searchPageHeader);
		System.out.println("Search Header: "+header);
		return	header;
	}
	
	public LoginPage navigateToLoginPage() {
		page.click(myAccMenu);
		page.click(loginOption);
		return new LoginPage(page);
	}

}
