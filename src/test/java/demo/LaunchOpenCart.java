package demo;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;

public class LaunchOpenCart {

	public static void main(String[] args) {
	
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
		      //page.locator("label.class='control-label':below(:text('E-Mail Address'))").fill("tnjprasannav@gmail.com");
		      page.fill("//input[@id='input-email']", "tnjprasannav@gmail.com");
		      page.fill("//input[@id='input-password']", "Test@123");
	
		      Locator loginButton =	page.locator("//input[@type='submit']");
		      loginButton.first().click();
		      
		      String title = page.title();
		      System.out.println("Title: "+title);
		      
		      Locator options =	page.locator("//div[@class='list-group']/a");
		      System.out.println(options.count());
		      
		      List<String> optionTextList = options.allTextContents();
		      /*	
		      for(String option :optionTextList) {
		    	  System.out.println(option);
		      }
		      */
		      
		     // optionTextList.forEach(opt -> System.out.println(opt));
		      /*
		      for(int i=0;i<options.count();i++) {
		    	  String textOptions = options.nth(i).textContent();
		    	  
		    	  if(textOptions.contains("Address Book")) {
		    		options.nth(i).click();
		    		break;
		    	  }
		      }
		      */
		      
		      IntStream.range(0, options.count())
		      .filter(i -> options.nth(i).textContent().contains("Address Book"))
		      .findFirst()
		      .ifPresent(i -> options.nth(i).click());
		      
		     
		      context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")) );
		      
		     // page.pause();
		      page.close();
		      browser.close();
		      playwright.close();
		      
		 }
		      
	}

}
