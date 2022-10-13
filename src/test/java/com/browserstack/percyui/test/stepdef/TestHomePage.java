package com.browserstack.percyui.test.stepdef;

import com.browserstack.percyui.test.Pages.HomePAge;
import com.browserstack.percyui.test.stepdef.TestRunner;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import java.util.Arrays;

public class TestHomePage extends TestRunner {

	@Test
	public void testHomePage()
	{
		HomePAge pg=new HomePAge(driver);
		pg.goToUrl();
		assertEquals(driver.getTitle(), pg.getHomeTitle());

		percy.snapshot("Home Page Test",Arrays.asList(new Integer[]{375, 480, 720, 1280, 1440, 1920}));
		//percy.snapshot("Single DOM Element",Arrays.asList(new Integer[]{375, 480, 720, 1280, 1440, 1920}),
		//		null,false,null,"#signupModalButton");
	}
}
