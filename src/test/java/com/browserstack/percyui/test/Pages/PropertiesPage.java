package com.browserstack.percyui.test.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class PropertiesPage extends AbstractPage{

    //private String title="Home Auctions | Real Estate Auctions | ServiceLink Auction";
    private String url="/properties";

    @FindBy(xpath="//*[@id=\"mainSearchBox\"]")
    private WebElement srchBox;

    public PropertiesPage(WebDriver driver) {
        super(driver);

    }
    public void goToUrl()
    {
        super.navigatetoUrl(super.baseUrl+url);
    }

    public String getPropertiesTitle()
    {
        return title;
    }

    public WebElement getSrchBox()
    {
        return fluientWaitforElement(srchBox,30,5);
    }

}
