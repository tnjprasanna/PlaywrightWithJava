package demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;

public class WindowPopUpPage {

	 public static Page page;
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Playwright playwright = Playwright.create()) {
		      LaunchOptions launchOpt = new LaunchOptions();
		      launchOpt.setChannel("chrome");
		      launchOpt.setHeadless(false);
		      Browser browser = playwright.chromium().launch(launchOpt);
		      BrowserContext context = browser.newContext(new Browser.NewContextOptions()
		                .setViewportSize(1920, 1080)); // Set desired resolution
		      
		      context.tracing().start(
		    		  new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		      
		      page = browser.newPage();
		      page.navigate("https://www.udemy.com/");
		      
		      /*
		      cookiesPopup();
		     Page popup = page.waitForPopup(() -> {
		    	 
		    	 Locator newLocator = page.locator("//a[@href='https://www.linkedin.com/company/orangehrm/mycompany/']"); // fresh context
		    	 newLocator.click();
		      });
		    
		      System.out.println("Popup "+popup.title());
		       */
		      Page popup = page.waitForPopup(() -> {
		    	 page.click("a[traget='_blank']"); 
		      });
		      popup.waitForLoadState();
		      popup.navigate("https://www.google.com");
		}
		
	}
	
	
	public static void cookiesPopup () {
		 try {
	    	  Locator allowAllButton = page.locator("button:has-text('Allow all')");
		      if (allowAllButton.isVisible()) 
		          allowAllButton.click();
		 	}catch (Exception e) {
		    	  e.printStackTrace();
		    }    
		 }

}
