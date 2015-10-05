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

public class ContactRemovalTests extends TestBase{
	@Test
	public void deleteSomeContact() {
		//save old list
		SortedListOf<ContactData>oldContactList=app.getContactHelper().getContacts();
		 Random rnd=new Random();
		 int index=rnd.nextInt(oldContactList.size()-1);
		
		//actions
		 app.getContactHelper().deleteContact(index);
		 //save new list
		 SortedListOf<ContactData>newContactList=app.getContactHelper().getContacts();
		  //compare
		 System.out.println("oldConactList:"+ oldContactList);
		 System.out.println("oldContactList.without(index):"+ oldContactList.without(index));
		 System.out.println("newContactList:"+ newContactList);
			assertThat(newContactList, equalTo(oldContactList.without(index)));
		 
		
		
	 }


}
