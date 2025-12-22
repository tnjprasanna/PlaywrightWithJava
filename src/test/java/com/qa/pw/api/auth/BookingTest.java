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

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class BookingTest {
	
	Playwright createPlaywright;
	APIRequest request;
	APIRequestContext apiReqContext;
	private static String TOKEN_ID=null;
	
	@BeforeTest
	public void setup() {
		System.out.println("Before Setup ");
		 createPlaywright =	Playwright.create();
		 request = createPlaywright.request();
		 apiReqContext = request.newContext();
		 
		 String reqTokenJsonBody = "{\r\n"
					+ "    \"username\" : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}";
			
			//Create token 
			APIResponse postApiTokenResponse = apiReqContext.post("https://restful-booker.herokuapp.com/auth",
					RequestOptions.create()
						.setHeader("Content-Type", "application/json")
						.setData(reqTokenJsonBody));
			
			 ObjectMapper mapper = new ObjectMapper();
		       JsonNode postJsonResponse =	mapper.readTree(postApiTokenResponse.body());
		       System.out.println(postJsonResponse.toPrettyString());
		       
		       System.out.println(postApiTokenResponse.status());
		       Assert.assertEquals(" Create/Post Booking: "+postApiTokenResponse.status(), 200);
		       
		       TOKEN_ID =	postJsonResponse.get("token").asString();
		       System.out.println("token ID: "+TOKEN_ID);
	}

	@Test(priority=1)
	public void updateBookingTest() throws IOException {
		
		String bookingJson = "{\r\n"
				+ "    \"firstname\" : \"James\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 555,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2025-01-01\",\r\n"
				+ "        \"checkout\" : \"2025-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Lunch\"\r\n"
				+ "}"; 
		
		//Create token 
		APIResponse putApiTokenResponse = apiReqContext.put("https://restful-booker.herokuapp.com/booking/1",
				RequestOptions.create()
					.setHeader("Content-Type", "application/json")
					.setHeader("Cookie","token="+TOKEN_ID)
					.setData(bookingJson));
	       
	       System.out.println(putApiTokenResponse.status());
	       Assert.assertEquals(" Update Booking: "+putApiTokenResponse.status(), 200);
	       
	       String responseText =putApiTokenResponse.text();
	    
	       ObjectMapper mapper = new ObjectMapper();
	       JsonNode putJsonResponse =	mapper.readTree(putApiTokenResponse.body());
	       System.out.println(putJsonResponse.toPrettyString());
	       
	       String token =	putJsonResponse.get("token").asString();
	       System.out.println("token ID: "+token);
	}
	
	@Test(priority=2)
	public void deleteBookingTest() {
		
		APIResponse deleteApiTokenResponse = apiReqContext.delete("https://restful-booker.herokuapp.com/booking/1",
				RequestOptions.create()
					.setHeader("Content-Type", "application/json")
					.setHeader("Cookie","token="+TOKEN_ID));
		
		   System.out.println("delete: "+ deleteApiTokenResponse.status());
	       Assert.assertEquals(deleteApiTokenResponse.status(), 201);
	       
	       String responseText =deleteApiTokenResponse.text();
	    
					
	}
	
	
	public void tearDown() {
		createPlaywright.close();		
	}

}
