package com.browserstack.percyui.test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePAge extends AbstractPage{

private String title="Home Auctions | Real Estate Auctions | ServiceLink Auction";
	
	private String url="";
	
	public HomePAge(WebDriver driver) {
		super(driver);
		
	}
	public void goToUrl()
	{
		super.navigatetoUrl(super.baseUrl+url);
	}

	public String getHomeTitle()
	{
		return title;
	}

	
}
