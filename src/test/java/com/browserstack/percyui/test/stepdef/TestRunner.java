package com.browserstack.percyui.test.stepdef;

import com.browserstack.local.Local;
import com.browserstack.percyui.test.Configuration.WebDriverMgr;
import com.browserstack.percyui.test.Model.BrowserStackOptions;
//import io.percy.selenium.Percy;
import io.percy.selenium.Percy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestRunner {

	public WebDriver driver;
	private Local l;
	public Percy percy;


	@BeforeMethod(alwaysRun = true)
	@org.testng.annotations.Parameters(value = { "config", "environment", "remote" })
	@SuppressWarnings("unchecked")
	public void setUp(String config_file, String environment, String remote, ITestContext context) throws Exception {
		System.out.println("inside BeforeMethod");
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader("src/test/conf/" + config_file));
		JSONObject envs = (JSONObject) config.get("environments");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
		Iterator it = envCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}

		System.out.println("Caps => " + config.get("capabilities"));
		Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
		it = commonCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> pair = (Map.Entry<String, Object>) it.next();
			Object envData = capabilities.getCapability(pair.getKey().toString());
			Object resultData = pair.getValue();
			if (envData != null && envData.getClass() == JSONObject.class) {
				((JSONObject) resultData).putAll((JSONObject) envData);
			}
			capabilities.setCapability(pair.getKey().toString(), resultData);
		}

		String username = System.getenv("BROWSERSTACK_USERNAME");
		if (username == null) {
			username = (String) config.get("user");
		}

		String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
		if (accessKey == null) {
			accessKey = (String) config.get("key");
		}

		System.out.println("LocaL:"+capabilities.getCapability("browserstack.local"));
		if (capabilities.getCapability("browserstack.local") != null
				&& capabilities.getCapability("browserstack.local").toString().equalsIgnoreCase("true")) {
			System.out.println("About to start local testing");
			l = new Local();
			Map<String, String> options = new HashMap<String, String>();
			options.put("key", accessKey);
			options.put("proxyHost", "127.0.0.1");
			options.put("proxyPort", "8888");
			l.start(options);
			System.out.println("l.start() method called!!");
			System.out.println("is Local running => "+l.isRunning());
			
		}

		BrowserStackOptions bsOptions = new BrowserStackOptions();
		bsOptions.setBsServer(config.get("server").toString());
		bsOptions.setCapabilities(capabilities);

		System.out.println("Printing capabilities...");
		Iterator itr = capabilities.asMap().entrySet().iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		if (remote.equalsIgnoreCase("true")) {

			String server = (String) config.get("server");
			System.out.println("Remote Server: https://" + username + ":" + accessKey + "@" + server + "/wd/hub");
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"),
					capabilities);
			percy = new Percy(driver);
		} else {
			WebDriverMgr mgr = new WebDriverMgr();
			driver = mgr.getWebDriver(environment);
			percy = new Percy(driver);
		}

		System.out.println("Env:" + environment + "; remote" + remote);

		context.setAttribute("bsOptions", bsOptions);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		if (l != null) {
			l.stop();
		}
	}
}
