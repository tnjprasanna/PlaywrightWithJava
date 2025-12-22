package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class HomePageTest extends BaseTest {
	
	@Test(priority=1)
	public void homePageTitleTest() {
		String actualTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void getHomePageURLTest() {
		String actualURL = homePage.getHomePageURL();
		Assert.assertEquals(actualURL, prop.getProperty("url"));
	}
	
	@DataProvider(name = "getProductData")
	public Object[][] getProductData(){	
		return new Object[][] {
			{"Macbook"},{"iMac"},{"Samsung"}
		};
	}
	
	@Test(priority=3,dataProvider = "getProductData")
	public void searchTest(String productName) {
		//String searchText = "macbook";
		String actualSearchHeader =	homePage.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}
	
	
	
}
