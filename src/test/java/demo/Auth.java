package demo;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.impl.junit.BrowserContextExtension;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Auth {

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
		      page.navigate("https://practicetestautomation.com/practice-test-login/");
		      page.fill("//input[@id='username']", "student");
		      page.fill("//input[@id='password']", "Password123");
		      
		      Locator loginButton =	page.locator("//button[@id='submit']");
		      loginButton.first().click();
		      context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("appLogin.json")));
		      
		 }

	}

}
