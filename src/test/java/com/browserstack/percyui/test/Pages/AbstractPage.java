package com.browserstack.percyui.test.Pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public abstract class AbstractPage {

	protected String baseUrl=System.getenv("BASE_URL");
	
	protected WebDriver driver;
	protected String title;

	public AbstractPage(WebDriver driver) {
		System.out.println("BasePage called: "+ driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigatetoUrl(String url) {
		driver.navigate().to(url);
	}

	public String getTitle() {
		title = driver.getTitle();
		return title;
	}

	public void clickifExists(WebElement elt) {
		fluientWaitforElement(elt, 30, 5).click();
		
	}

	public void sendKeysifExist(WebElement elt, String txt) {
		fluientWaitforElement(elt, 30, 5).sendKeys(txt);
		
	}
	
	public boolean ifElementVisible(WebElement elt)
	{
		boolean visibility=false;
		WebElement eltFound=fluientWaitforElement(elt, 30, 5);
		if(eltFound !=null)
		{
			visibility=true;
		}
		return visibility;
		
	}
	
	public WebElement fluientWaitforElement(WebElement element, int timoutSec, int pollingSec) {

		//System.out.println(driver.getTitle());
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timoutSec))
				.pollingEvery(Duration.ofSeconds(pollingSec))
				.ignoring(NoSuchElementException.class, TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);

		for (int i = 0; i < 2; i++) {
			try {

				fWait.until(ExpectedConditions.visibilityOf(element));
				fWait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e) {

				System.out.println("Element Not found trying again - " + element.toString().substring(70));
				e.printStackTrace();

			}
		}

		return element;

	}
}
