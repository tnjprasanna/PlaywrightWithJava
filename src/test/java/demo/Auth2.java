package demo;

import java.nio.file.Paths;
import java.util.stream.IntStream;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Auth2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		      page.navigate("https://practicetestautomation.com/courses/");
		      
		      Locator courseTitle =	page.locator("h1.post-title");
		      
		      if(courseTitle.isVisible())
		    	  System.out.println("Course Book clicked");
		      
		      page.pause();
		 }

	}

}
