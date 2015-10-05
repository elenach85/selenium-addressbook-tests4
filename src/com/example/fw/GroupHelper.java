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
	//private List<GroupData>cachedGroups;
	private SortedListOf<GroupData>cachedGroups;
	//public List<GroupData> getGroups() {
	public SortedListOf<GroupData> getGroups() {
		if (cachedGroups==null){
		rebuildCache();
		}
		return cachedGroups;
		}
	
		private void rebuildCache() {
	//		cachedGroups= new ArrayList<GroupData>();
			cachedGroups= new SortedListOf<GroupData>();
			   manager.navigateTo().groupsPage();
			List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
			for (WebElement checkbox : checkboxes) {
			String title=checkbox.getAttribute("title");
				String name=title.substring("Select (".length(),title.length()-")".length());
				cachedGroups.add(new GroupData().withName(name));	
		
	}
		}
		
	
	public GroupHelper createGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		    initGroupCreation();
		  	fillGroupForm(group);
		    submitGroupCreation();
		    returnGroupsPage();
		    rebuildCache();
		    return this;
		
	}
	
	public GroupHelper deleteGroup(int index) {
		//manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupRemoval();
		returnGroupsPage();
		rebuildCache();
		return this; 	
		}
	
	
	public GroupHelper modifyGroup(int index, GroupData group){
		//manager.navigateTo().groupsPage();
	    initGroupModification(index);
	    fillGroupForm(group);
		submitGroupModification();
		 returnGroupsPage();
		rebuildCache();
		return this;
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------------		
	public  GroupHelper initGroupCreation() {
	    click(By.name("new"));
	    return this;
}
	
	public GroupHelper submitGroupCreation() {
		    click(By.name("submit"));
		    cachedGroups=null;
		    return this;
	}
	public GroupHelper returnGroupsPage() {
	   click(By.linkText("group page"));
	   return this;
}
	public GroupHelper fillGroupForm(GroupData group) {
		type(By.name("group_name"), group.getName());
		type(By.name("group_header"), group.getHeader());
		type(By.name("group_footer"), group.getFooter());
		return this;		
	}
	
	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]']["+ (index+1)+"]"));
 
	}
	public GroupHelper initGroupModification(int index) {
	selectGroupByIndex(index);
	click(By.name("edit"));
	return this; 	 
	}
	public GroupHelper submitGroupModification() {
		click(By.name("update"));	
		   cachedGroups=null;
		return this; 
	}
	
	public void submitGroupRemoval() {
		click(By.name("delete"));
		   cachedGroups=null;
	}
	
}
