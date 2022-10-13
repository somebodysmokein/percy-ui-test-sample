package com.browserstack.percyui.test.Configuration;

import com.browserstack.percyui.test.Model.User;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Hashtable;

public class WebDriverMgr {

	private WebDriver driver;
	// private static WebDriverMgr instance=null;
	private boolean latestVersion = true;
	private String browserVersion;
	private Hashtable<String, String> capabilities;

	public WebDriverMgr() {

	}

	public WebDriver getWebDriver(String browser) {
		return getDriver(browser);

	}

	
	/**
	 * Provides the right driver based on the settings
	 */
	private WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {

			System.out.println("Running Chrome..");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		} else if (browser.equalsIgnoreCase("FireFox")) {

			System.out.println("Running Firefox..");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} else if (browser.equalsIgnoreCase("BrowserStack")) {

			String USERNAME = User.getInstance().getUserName();
			String AUTOMATE_KEY = User.getInstance().getPassword();
			
			//do nothing

		}else if(browser.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		return driver;

	}
}
