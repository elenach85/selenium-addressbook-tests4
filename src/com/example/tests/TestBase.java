package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;
import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static com.example.tests.ContactDataGenerator.generateRandomContacts;
import static com.example.tests.ContactDataGenerator.loadContactsFromCsvFile;
import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;

import com.thoughtworks.selenium.webdriven.commands.GetText;
public class TestBase {
	protected static ApplicationManager app;
	

		@BeforeTest
	public void setUp() throws Exception {
			String configFile = System.getProperty("configFile","application.properties");
			Properties properties = new Properties();
			properties.load(new FileReader(new File(configFile)));
			app=new ApplicationManager(properties);
			
	   
	  }

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	    
	  }
	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator(){
		return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
	}
	
	public static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]>list=new ArrayList<Object[]>();
		for (GroupData group : groups) {
		list.add(new Object[]{group});
		}
		return list;
	}

	@DataProvider 
	public Iterator<Object[]> contactsFromXmlFile() throws IOException{
	return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
		
	}
	
	@DataProvider 
	public Iterator<Object[]> contactsFromCsvFile() throws IOException{
	return wrapContactsForDataProvider(loadContactsFromCsvFile(new File("contacts.txt"))).iterator();
		
	}
	
	@DataProvider
	public Iterator<Object[]> groupsFromCsvFile() throws IOException{
	return wrapGroupsForDataProvider(loadGroupsFromCsvFile(new File("groups.txt"))).iterator();
	}
	@DataProvider
	public Iterator<Object[]> groupsFromXmlFile() throws IOException{
	return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}

	public String generateRandomYear() {
		Random rnd=new Random(); 
		if (rnd.nextInt(7)==0) {
			return " ";	
			} else {
				String year;
				year=String.valueOf(1920+rnd.nextInt(95));
			return year;
			}	
	}
	
public String generateRandomMonth() {
		Random rnd=new Random(); 
		if (rnd.nextInt(7)==0) {
			return "-";	
			} else
			{
				int number;
				String[] monthName = {
						"January", 
						"February",
						"March",
						"April",
						"May",
						"June",	
						"July",
						"August",
						"September",
						"October",
						"November",
						"December",
						};
				number=rnd.nextInt(12);
				String month=monthName[number];	
			return month;
			}	
	}
protected String generateRandomDay(){
	String day;
	Random rnd=new Random(); 
	  Calendar cal = Calendar.getInstance();
	  int dayInt = cal.get(Calendar.DATE);
	
	  if (rnd.nextInt(7)==0) {
		  return day="-";		
	} else {
		 dayInt=rnd.nextInt(30);
		  day=String.valueOf(dayInt);
return day;
	}		
		}
	 

	@DataProvider
	public Iterator<Object[]> randomValidContactGeneratorWithoutGroupName() {
		return wrapContactsForDataProvider(generateRandomContacts(3)).iterator();
	}

	public static List<Object[]> wrapContactsForDataProvider(List<ContactData> contacts) {
		List<Object[]>contactList=new ArrayList<Object[]>();
		for (ContactData contact : contacts) {
			contactList.add(new Object[]{contact});	
		}
		return contactList;
	}

	public  String generateRandomString(){
		Random rnd=new Random(); 
		if (rnd.nextInt(3)==0) {
			return " ";	
			} else {
			return "test"+ rnd.nextInt();
			}	
	
}
	@DataProvider
	public Iterator<Object[]> randomValidContactGeneratorWithGroupName() {
app.navigateTo().mainPage();
app.getContactHelper().initContactCreation();
app.getContactHelper().getGroupsNameList();
	List<Object[]>contactList=new ArrayList<Object[]>();
		for (int i = 0; i <1; i++) {
			ContactData contact=new ContactData()
					.withFirstname(generateRandomString())
					.withLastname(generateRandomString())
					.withAddress1(generateRandomString())
					.withHomeTel(generateRandomString())
					.withMobile_Tel(generateRandomString())
					.withWorktel(generateRandomString())
					.withEmail(generateRandomString())
					.withBirthDay(generateRandomDay())
					.withBirthYear(generateRandomYear())
					.withBirthMonth(generateRandomMonth())
					.withEmail2(generateRandomString())
					.withPhone2(generateRandomDay());
		//.withGroupName(app.getContactHelper().randomGroupSelection());
	contactList.add(new Object[]{contact});
	}
	return contactList.iterator();
	}

}
	
