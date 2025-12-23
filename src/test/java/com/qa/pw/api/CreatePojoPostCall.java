package com.qa.pw.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.pw.api.data.User;

import tools.jackson.databind.ObjectMapper;

public class CreatePojoPostCall {
	
	Playwright createPlaywright;
	APIRequest request;
	APIRequestContext apiReqContext;
	
	//@BeforeTest
	public void setup() {
		
		 createPlaywright =	Playwright.create();
		 request = createPlaywright.request();
		 apiReqContext = request.newContext();
	}

	//@Test
	public void createUserTest() throws IOException {
		
		User user = new User("1002","Suraj", "suraj@gmail.com", "Jihani", "044234242", 41, 42000);
		
		 APIResponse postApiResponse = apiReqContext.post("http://localhost:3000/userDetails",
					RequestOptions.create()
						.setHeader("Content-Type", "application/json")
						.setData(user));
		       
		       System.out.println(postApiResponse.status());
		       Assert.assertEquals(postApiResponse.status(), 201);
		       
		       String responseText =postApiResponse.text();
		       //Desearialize 
		       ObjectMapper mapper = new ObjectMapper();
		       User actualUser = mapper.readValue(responseText, User.class);
		       
		       Assert.assertEquals(actualUser.getFirstName(), user.getFirstName());
		       Assert.assertEquals(actualUser.getEmail(), user.getEmail());
		       Assert.assertEquals(actualUser.getLocation(), user.getLocation());
		       Assert.assertEquals(actualUser.getPhoneNum(), user.getPhoneNum());
		       Assert.assertEquals(actualUser.getAge(), user.getAge());
		       Assert.assertEquals(actualUser.getSalary(), user.getSalary());
		       Assert.assertNotNull(actualUser.getId());
	}
	//@AfterTest
	public void tearDown() {
		createPlaywright.close();		
	}
}
