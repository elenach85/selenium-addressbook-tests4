package com.example.fw;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Properties;

public class ApplicationManager {
	public static WebDriver driver;
	public  String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	public NavigationHelper navigationHelper;
	public GroupHelper groupHelper;
	public ContactHelper contactHelper;
	private Properties properties;

	public  ApplicationManager(Properties properties){
		this.properties = properties;
		String browser=properties.getProperty("browser");
		if ("firefox".equals(browser)) {
			driver = new FirefoxDriver();	
		} else 
		if ("ie".equals(browser)) {
			//System.setProperty("webdriver.ie.driver", "C:\IEDriver\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();	
		}
		else {
			throw new Error("Unsupported browser:"+ browser);
		}
		baseUrl=properties.getProperty("baseUrl");
		
	   // baseUrl = "http://localhost";
	  //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(baseUrl);
	}
		public void stop() {
		driver.quit();
	   		
	}
public NavigationHelper navigateTo(){
	if (navigationHelper==null) {
	navigationHelper=new NavigationHelper(this);	
	}
	return navigationHelper;
}
public GroupHelper getGroupHelper(){
if (groupHelper==null) {
	groupHelper=new GroupHelper(this);
}
return groupHelper;
}
public ContactHelper getContactHelper(){
	if (contactHelper==null) {
	contactHelper=new ContactHelper(this);	
	}
	return contactHelper;
}
}

