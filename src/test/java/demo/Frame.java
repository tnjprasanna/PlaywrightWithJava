package demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Frame {

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
		      
		      Page page = browser.newPage();
		      
		      page.navigate("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		      
		      page.locator("//img[@alt='Vehicle Registration Form']").first().click();
		      
		      page.frameLocator("//iframe[contains(@id,'frame-one')]")
		     	.locator("RESULT_TextField-8").fill("Prasanna Venkatesan");
		     
		      
		 }

	}

}
