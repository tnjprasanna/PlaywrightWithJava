package demo;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class FilesToUpload {

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
		      page.navigate("https://practice.expandtesting.com/upload");
		      page.setInputFiles("button#fileSubmit", new Path[] {
		    		  
		    		  Paths.get("appLogin.json")
		    		
		      });
		      
		}
	}
}
