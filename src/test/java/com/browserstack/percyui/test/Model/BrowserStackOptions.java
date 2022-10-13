package com.browserstack.percyui.test.Model;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackOptions {

	public String bsServer;
	public DesiredCapabilities capabilities;
	
	
	public String getBsServer() {
		return bsServer;
	}
	public void setBsServer(String bsServer) {
		this.bsServer = bsServer;
	}
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}
	
	
	
}
