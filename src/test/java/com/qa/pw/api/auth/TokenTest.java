package com.qa.pw.api.auth;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.pw.api.data.User;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class TokenTest {
	
	Playwright createPlaywright;
	APIRequest request;
	APIRequestContext apiReqContext;
	
	@BeforeTest
	public void setup() {
		
		 createPlaywright =	Playwright.create();
		 request = createPlaywright.request();
		 apiReqContext = request.newContext();
	}

	@Test
	public void getTokenTest() throws IOException {
		
		String reqTokenJsonBody = "{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
		
		//Create token 
		APIResponse postApiTokenResponse = apiReqContext.post("https://restful-booker.herokuapp.com/auth",
				RequestOptions.create()
					.setHeader("Content-Type", "application/json")
					.setData(reqTokenJsonBody));
	       
	       System.out.println(postApiTokenResponse.status());
	       Assert.assertEquals(postApiTokenResponse.status(), 200);
	       
	       String responseText =postApiTokenResponse.text();
	    
	       ObjectMapper mapper = new ObjectMapper();
	       JsonNode postJsonResponse =	mapper.readTree(postApiTokenResponse.body());
	       System.out.println(postJsonResponse.toPrettyString());
	       
	       String token =	postJsonResponse.get("token").asString();
	       System.out.println("token ID: "+token);
	       	
	}
	
	public void tearDown() {
		createPlaywright.close();		
	}
	
}
