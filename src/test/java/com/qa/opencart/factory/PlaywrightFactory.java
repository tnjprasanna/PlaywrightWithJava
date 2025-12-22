package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class PlaywrightFactory {
	
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;
	Properties prop;
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> tlbrowserContext = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<Page>();
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<Playwright>();
	
	public static Browser getBrowser() {
		return tlBrowser.get();
	}
	
	public static BrowserContext getBrowserContext() {
		return tlbrowserContext.get();
	}
	
	public static Page getPage() {
		return tlPage.get();
	}
	
	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}
	
	
	
	public Page initBrowser(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is: "+browserName);
		
		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			//browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			//browser = playwright.chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			break;
		default:
			break;
		}
		
		//browserContext = browser.newContext();
		tlbrowserContext.set(getBrowser().newContext());
		//page = browserContext.newPage();
		tlPage.set(getBrowserContext().newPage());		
		//page.navigate(prop.getProperty("url").trim());
		getPage().navigate(prop.getProperty("url").trim());
		//return page;
		return getPage();
	}
	
	public Properties init_pop() throws FileNotFoundException {
		
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
			prop = new Properties();
			prop.load(ip);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public static String takeScreenShot() {
		
		String path = System.getProperty("user.dir")+ "/screenshot/" + System.currentTimeMillis() + ".png";
		byte buffer[] = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64_Path = Base64.getEncoder().encodeToString(buffer);
		return base64_Path;
		
	}

}
