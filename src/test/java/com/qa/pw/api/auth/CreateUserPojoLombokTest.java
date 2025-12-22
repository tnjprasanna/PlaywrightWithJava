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

import tools.jackson.databind.ObjectMapper;

public class CreateUserPojoLombokTest {
	
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
	public void createUserTest() throws IOException {
		
		
		User user = new User("1002","Suraj", "suraj@gmail.com", "Jihani", "044234242", 41, 42000);
		
		/*
		Users user = Users.builder()
		        .firstName("Prasanna")
		        .salary("50000")
		        .phoneNum("1234567890")
		        .location("Bangalore")
		        .email("test@example.com")
		        .age("30")
		        .skills(new Skills())
		        .build();
		 	*/

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

}
