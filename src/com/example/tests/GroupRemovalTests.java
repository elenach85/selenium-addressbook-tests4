package com.example.tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matcher.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase {
	 @Test
public void deleteSomeGroup() {
		 //save old state
		 SortedListOf<GroupData> oldList=app.getGroupHelper().getGroups();
		 Random rnd=new Random();
		 int index=rnd.nextInt(oldList.size()-1);
		 //actions
				 app.getGroupHelper().deleteGroup(index);
		 		
		 //save new state
		SortedListOf<GroupData> newList=app.getGroupHelper().getGroups();
		 //compare states
		 //oldList.remove(index);
		// Collections.sort(oldList);
		 //assertEquals(newList, oldList);
		 assertThat(newList,equalTo(oldList.without(index)));
	 }
	 }

