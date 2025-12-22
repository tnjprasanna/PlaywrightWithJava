package demo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class App {
	
	 public static void main(String[] args) {
		    try (Playwright playwright = Playwright.create()) {
		    	
		    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		    	int width =(int)screenSize.getWidth();
		    	int height = (int)screenSize.getHeight();
		    	System.out.println(width +":"+ height);
		    	
		      LaunchOptions launchOpt = new LaunchOptions();
		      launchOpt.setChannel("chrome");
		      launchOpt.setHeadless(false);
		      Browser browser = playwright.chromium().launch(launchOpt);
		      BrowserContext context = browser.newContext(new Browser.NewContextOptions()
		                .setViewportSize(width, height)); // Set desired resolution
		      
		      context.tracing().start(
		    		  new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		      
		      Page page = browser.newPage();
		      page.navigate("https://playwright.dev/");
		      page.waitForTimeout(3000);
		      String title = page.title();
		      System.out.println("Title: "+title);
		      context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")) );
		      page.close();
		      browser.close();
		      playwright.close();
		     // page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
		    }
		    
		  }
}
