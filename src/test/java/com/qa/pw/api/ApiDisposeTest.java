package com.qa.pw.api;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.HttpHeader;

public class ApiDisposeTest {

	Playwright createPlaywright;
	APIRequest request;
	APIRequestContext apiReqContext;
	
	@BeforeTest
	public void setup() {
		
		 createPlaywright =	Playwright.create();
		 request = createPlaywright.request();
		 apiReqContext = request.newContext();
	}
	
	//@Test
	public void apiDisposeResponse() {
		
		APIResponse apiResponse =	apiReqContext.get("http://localhost:3000/userDetails");
		int statucCode = apiResponse.status();
		System.out.println(statucCode);
		Assert.assertEquals(200, statucCode);
		System.out.println(apiResponse.text());
		
		
		List<HttpHeader> headerList = apiResponse.headersArray();
		
		for(HttpHeader e : headerList ) {
			System.out.println("Name: "+e.name+" Value: "+e.value);
		}
		
		headerList.forEach(e -> System.out.println("Name: " + e.name + " Value: " + e.value));
		
		
		//apiResponse.dispose(); //Dispose method will dispose only response body 
		try {
			String text = apiResponse.text();
			//System.out.println(text);
		} catch(PlaywrightException e) {
			e.printStackTrace();
		}
		//System.out.println(apiResponse.url());
		
		
	}
	
	public void tearDown() {
		createPlaywright.close();		
	}
}
