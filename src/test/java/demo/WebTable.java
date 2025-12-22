package demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;

public class WebTable {

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
			      //page.locator("label.class='control-label':below(:text('E-Mail Address'))").fill("tnjprasannav@gmail.com");
			      
			      
			      page.navigate("https://cosmocode.io/automation-practice-webtable/");
			      //page.locator("tr:has(td:has-text('Algeria')) input.hasVisited").click();
			      
			      //Locator country = page.locator("//table[@id='countries']//td[strong[text()='Algeria']]");
			      //country.click();
			      
			      Locator row =	page.locator("//table[@id='countries']//tr");
			      row.locator(":scope", new Locator.LocatorOptions().setHasText("Algeria")).locator("//input[@class='hasVisited']").click();
			      row.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));
			      //page.pause();
			 }

	}

}
