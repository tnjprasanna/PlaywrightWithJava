package demo;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class JSAlert {
	
	public static void main(String[] args) {
			
		try (Playwright playwright = Playwright.create()) {
		      LaunchOptions launchOpt = new LaunchOptions();
		      launchOpt.setChannel("chrome");
		      launchOpt.setHeadless(false);
		      Browser browser = playwright.chromium().launch(launchOpt);
		      BrowserContext context = browser.newContext(new Browser.NewContextOptions()
		                .setStorageStatePath(Paths.get("appLogin.json"))); // Set desired resolution
		      
		      context.tracing().start(
		    		  new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		      Page page = browser.newPage();
		      
		      page.onDialog(dialog -> {
		    	  
		    	  String text = dialog.message();
		    	  System.out.println(text);
		    	  dialog.accept("alert from PV");
		      });
		      
		      page.navigate("https://the-internet.hackerearth.com/javascript_alerts");
		      
		      page.click("//button[text()='Click for JS Alert']");
		      String result =	page.textContent("#result");
		      System.out.println(result);
		      
		      page.click("//button[text()='Click for JS Confirm']");
		      String confirmResult =	page.textContent("#result");
		      System.out.println(confirmResult);
		      
		      page.click("//button[text()='Click for JS Prompt']");
		      String promptResult =	page.textContent("#result");
		      System.out.println(promptResult);
			}
	
		}
	      
	}

