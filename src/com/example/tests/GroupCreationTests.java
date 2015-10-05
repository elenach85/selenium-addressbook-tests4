package com.example.tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import  org.testng.annotations.Test;
import static com.example.tests.TestBase.wrapGroupsForDataProvider;
import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matcher.*;
import com.example.utils.ListOf;
import com.example.utils.SortedListOf;
import com.google.common.collect.Lists;
public class GroupCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> groupsFromFile() throws IOException{
	return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}
	


@Test(dataProvider="groupsFromFile")
  public void testGroupCreationWithValidData(GroupData group) throws Exception {

    // save old state
	  SortedListOf<GroupData> oldList=app.getGroupHelper().getGroups();
       //actions
    app.getGroupHelper().createGroup(group);
  
    //save new state
    SortedListOf<GroupData> newList=app.getGroupHelper().getGroups();
    //compare state
assertThat(newList,equalTo(oldList.withAdded(group)));

  }


}
