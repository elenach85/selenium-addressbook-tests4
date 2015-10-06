package com.example.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase {
	 @Test(dataProvider="groupsFromXmlFile")
public void modifySomeGroup(GroupData group) {
		 //save old state
		 SortedListOf<GroupData>oldList=app.getGroupHelper().getGroups();
		 Random rnd=new Random();
		 int index=rnd.nextInt(oldList.size()-1);
		 //actions
		 app.getGroupHelper().modifyGroup(index,group);
		//save new state
		 SortedListOf<GroupData>newList=app.getGroupHelper().getGroups();
		 //compare states
		 //Collections.sort(oldList);
		// assertEquals(newList, oldList);
		 assertThat(newList,equalTo(oldList.without(index).withAdded(group)));
		
	 }

}
