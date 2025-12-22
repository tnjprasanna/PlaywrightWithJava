package com.qa.pw.api;

import java.io.File;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class GetApiCall {
	
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
	public void getUserApiTest() {
		
		APIResponse apiResponse =	apiReqContext.get("http://localhost:3000/userDetails");
		int statucCode = apiResponse.status();
		System.out.println(statucCode);
		Assert.assertEquals(200, statucCode);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonResponse =	mapper.readTree(apiResponse.body());
		String jsonPrettyResp =	jsonResponse.toPrettyString();
		System.out.println(jsonPrettyResp);
		
		Map<String, String> headersMap =	apiResponse.headers();
		System.out.println(headersMap);
	}
	
	//@Test
	public void getSpecificUserApiTest() {
		
		APIResponse apiResponse =	apiReqContext.get(
				"http://localhost:3000/userDetails",
					RequestOptions.create()
						.setQueryParam("id", "cf20")
							.setQueryParam("salary", 25000));
		
		int statucCode = apiResponse.status();
		System.out.println(statucCode);
		Assert.assertEquals(200, statucCode);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonResponse =	mapper.readTree(apiResponse.body());
		String jsonPrettyResp =	jsonResponse.toPrettyString();
		System.out.println(jsonPrettyResp);
		
	}
	
	@Test
	public void createUser() throws IOException {
		
		/*
		// Create using Java object
        Skill skill = new Skill();
        skill.setFirst("NodeJS");
        skill.setSecond("AngularJS");
        skill.setThird("ReactJS");	
        
		Map<String, Object> data = new HashMap<String,Object>();
			data.put("FirstName", "PwUser1");
			data.put("email", "PwUser1@gmail.com");
			data.put("location", "hyd");
			data.put("phoeNum", "147852");
			data.put("age", 35);
			data.put("Salary", 35000);
			data.put("skills",Arrays.asList(skill));
			*/
		
			//Get JSON from file. 
			byte[] fileBytes = null;
			File jsonFile = new File("./src/test/resources/user.json");
			fileBytes = Files.readAllBytes(jsonFile.toPath());
			
	       APIResponse postApiResponse = apiReqContext.post("http://localhost:3000/userDetails",
				RequestOptions.create()
					.setHeader("Content-Type", "application/json")
					.setData(fileBytes));
	       
	       System.out.println(postApiResponse.status());
	       Assert.assertEquals(postApiResponse.status(), 201);
	       
	       System.out.println(postApiResponse.text());
	       
	       ObjectMapper mapper = new ObjectMapper();
	       JsonNode postJsonResp =	mapper.readTree(postApiResponse.body());
	       System.out.println(postJsonResp.toPrettyString());
		}
	
	public void tearDown() {
		createPlaywright.close();		
	}
}
