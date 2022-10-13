package com.browserstack.percyui.test.stepdef;

import com.browserstack.percyui.test.Pages.PropertiesPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class TestProperties extends TestRunner{

    @Test
    public void testPropertiesPage() throws InterruptedException {
        PropertiesPage pg=new PropertiesPage(driver);
        pg.goToUrl();
        //assertEquals(driver.getTitle(), pg.getPropertiesTitle());
        WebElement srchElt= pg.getSrchBox();
        srchElt.click();
        srchElt.sendKeys("27544");
        Thread.sleep(3000);
        percy.snapshot("Properties Page Test", Arrays.asList(new Integer[]{375, 480, 720, 1280, 1440, 1920}));

    }
}
