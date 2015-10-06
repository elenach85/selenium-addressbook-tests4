package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import javax.swing.border.TitledBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.tests.TestBase;
import com.example.utils.SortedListOf;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class GroupHelper extends HelperBase  {
	 
	
	public GroupHelper(ApplicationManager manager) {
		super(manager);
		}
	
	private SortedListOf<GroupData>cachedGroups;
	
	public SortedListOf<GroupData> getGroups() {
		if (cachedGroups==null){
		rebuildCache();
		}
		return cachedGroups;
		}
	
		private void rebuildCache() {
			cachedGroups= new SortedListOf<GroupData>();
			   manager.navigateTo().groupsPage();
			List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
			for (WebElement checkbox : checkboxes) {
			String title=checkbox.getAttribute("title");
				String name=title.substring("Select (".length(),title.length()-")".length());
				cachedGroups.add(new GroupData().withName(name));	
		
	}
		}
		
	
	public void createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		    initGroupCreation();
		  	fillGroupForm(group);
		    submitGroupCreation();
		    returnGroupsPage();
		    rebuildCache();
		   
		
	}
	
	public void deleteGroup(int index) {
		selectGroupByIndex(index);
		submitGroupRemoval();
		returnGroupsPage();
		rebuildCache();
		}
	
	
	public void modifyGroup(int index, GroupData group){
	    initGroupModification(index);
	    fillGroupForm(group);
		submitGroupModification();
		 returnGroupsPage();
		rebuildCache();	
	}
	
	//-------------------------------------------------------------------------------------------------------------------------		
	public  void initGroupCreation() {
	    click(By.name("new"));
	    
}
	
	public void submitGroupCreation() {
		    click(By.name("submit"));
		  
	}
	public void returnGroupsPage() {
	   click(By.linkText("group page"));
}
	public void fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());	
	}
	
	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]']["+ (index+1)+"]"));
 
	}
	public void initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit")); 
	}
	public void submitGroupModification() {
		click(By.name("update"));	
		  
	
	}
	
	public void submitGroupRemoval() {
		click(By.name("delete"));
		 
	}
	
}
