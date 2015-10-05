package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	
	}

	public void mainPage() {
		if (!onMainPage()) {
		click(By.linkText("home"));
	 
	}
	}

	public void groupsPage() {
		if (!onGroupsPage()) {
			click(By.linkText("groups"));
		}
		   	}
	public void goToSubmittedGroupPage() { 
		click(By.xpath("//*[@id='content']/div/i/a"));
	}
	public boolean onGroupsPage() {
		if (driver.findElements(By.name("new")).size()>0 && driver.getCurrentUrl().contains("/group.php")) {	
		
		return true;
		}
		else{
			return false;
		}
		}
	public boolean onMainPage() {
		if (driver.findElements(By.id("maintable")).size()>0) {
			return true;	
		} else {
			return false;
		}
	
	}
	public boolean onCreateContactPage() {
		if (driver.findElements(By.xpath("//input[@value='Enter']")).size()>0){
		
		return true;
		}
		else {
			return false;
		}
	}
}


	

	/**
	 * 
	 */
		


