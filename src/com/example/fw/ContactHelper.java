 package com.example.fw;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {
	public static boolean CREATION=true;
	public static boolean MODIFICATION=false;
	private List<WebElement> groupNames;

	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	private SortedListOf<ContactData>cachedContacts;
	//private List<ContactData>cachedContacts;
	//private ListOf<ContactData>cachedContacts;
	public SortedListOf<ContactData> getContacts() {
	//public ListOf<ContactData> getContacts() {
	//public List<ContactData> getContacts() {
		if (cachedContacts==null) {
			rebuildCache();
		}
		return cachedContacts;
		}
		
	private void rebuildCache() {
      // cachedContacts=new ArrayList<ContactData>();	
	cachedContacts=new SortedListOf<ContactData>();
        //cachedContacts=new ListOf<ContactData>();
		List<WebElement> rows = findElements();
		for (WebElement row : rows) {
			List<WebElement>columns =row.findElements(By.tagName("td"));
			ContactData contact=new ContactData();
			String last_name_title=columns.get(1).getText();
			String first_name_title=columns.get(2).getText();
			String email=columns.get(3).getText();
			String home_tel=columns.get(4).getText();
			String id=columns.get(0).findElement(By.xpath(".//input[@value]")).getAttribute("value");
			//System.out.println(id);
	cachedContacts.add(new ContactData().withFirstname(first_name_title).withLastname(last_name_title).withEmail(email).withHomeTel(home_tel).withId(id));

		}
		}

	public ContactHelper createContact(ContactData contact) {
		initContactCreation();
		fillContactCreationForm(contact,CREATION);	
		submitContactCreation();
	    manager.navigateTo().mainPage();
	    rebuildCache();
		return this;	
	}

	public ContactHelper modifyContact(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		selectContact(index);
		fillContactCreationForm(contact,MODIFICATION);
	    submitContactModification();
		manager.navigateTo().mainPage();
		rebuildCache();
		checkContact(index);
	    randomGroupSelection();
		submitGroupChange();
		manager.navigateTo().goToSubmittedGroupPage();
		manager.navigateTo().mainPage();
		
	return this;
}
	public ContactHelper deleteContact(int index) {
		selectContact(index);
		deleteContact();
		manager.navigateTo().mainPage();
		rebuildCache();
	return this;	
	}
	
	
//-------------------------------------------------------------------------------------------------------------------------	
	
	
	
	public ContactHelper initContactCreation() {
		if (!manager.navigationHelper.onCreateContactPage()){
			driver.findElement(By.linkText("add new")).click();
			
		}
		return this;	
	}
	
	public ContactHelper fillContactCreationForm(ContactData contact,boolean formType) {
		//if (driver.findElements(By.name("submit")).size()>0) {
		//formType=CREATION;	
		//}
		if(formType==CREATION)
		{
		selectByText(By.name("new_group"),contact.getGroup_name());	
		}
		type(By.name("firstname"),contact.getFirst_name());
		type(By.name("lastname"),contact.getLast_name());
	  	type(By.name("address"),contact.getAddress_1());
		type(By.name("home"),contact.getHome_tel());
	  	type(By.name("mobile"),contact.getMobile_tel());
	    type(By.name("work"),contact.getWork_tel());
		type(By.name("email"),contact.getEmail());
		type(By.name("email2"),contact.getEmail2());
		type(By.name("byear"),contact.getBirth_year());
	   	selectByText(By.name("bmonth"), contact.getBirth_month());
	   	selectByText(By.name("bday"), contact.getBirth_day());
	    type(By.name("address2"),contact.getAddress_2());
	    type(By.name("phone2"),contact.getPhone_2());
	
	    return this;
	}
	
	public ContactHelper submitContactCreation() {
		driver.findElement(By.name("submit")).click();
		cachedContacts=null;
		return this;
	}

	
	public ContactHelper selectContact(int index) {
	click(By.xpath("//tr[@name='entry'][" + (index+1) + "]/td/a/img[@title='Edit']"));
	return this;
	}

	public ContactHelper deleteContact() {
	click(By.xpath("//input[@value='Delete']"));
	cachedContacts=null;
	return this;
	}
	public ContactHelper checkContact(int index) {
	    click(By.xpath("(//input[@type='checkbox'])[" + (index+1) + "]"));
	    return this;	
		}
	  public ContactHelper submitContactModification() {
		  click(By.xpath("//input[@value='Update']"));
		  cachedContacts=null;
		  return this;
	}

	public ContactHelper submitGroupChange() {
	click(By.xpath("//input[@name='add']"));
	return this;	
	}

	private List<WebElement> findElements() {
		return driver.findElements(By.name("entry"));
	}
	public List<WebElement> getGroupsNameList(){
		if (manager.navigationHelper.onCreateContactPage()==true) {
			List<WebElement>groupNames=driver.findElements(By.xpath("//select[@name='new_group']/option"));
			return groupNames;
		} else {if (manager.navigationHelper.onMainPage()==true) {
			List<WebElement>groupNames=driver.findElements(By.xpath("//select[@name='to_group']/option"));
			return groupNames;	
		}
		return groupNames;		
		}
		}		
	public String randomGroupSelection(){
		List<WebElement> groupNamesList=getGroupsNameList();
		Random rnd=new Random();
		int index=rnd.nextInt(groupNamesList.size());
		// checkContact(index);
		if (manager.navigationHelper.onMainPage()==true) {
			selectGroupForChange(index);	
		} else {if (manager.navigationHelper.onCreateContactPage()==true)
		 {
			selectGroupForContactCreation(index);	
		}
		}
		 String groupName;
		 groupName=groupNamesList.get(index).getText();
		 return groupName;
	}
	
	public ContactHelper selectGroupForContactCreation(int index2) {
		
	     click(By.xpath("(//select[@name='new_group']/option)[" + (index2+1) + "]"));
	     return this;			  
		}
	
	public ContactHelper selectGroupForChange(int index2) {
	      click(By.xpath("(//select[@name='to_group']/option)[" + (index2+1) + "]"));
	      return this;			  
		}
			  
			  
	public WebElement selectId(int index)	{
	WebElement id = driver.findElement(By.xpath("(//input[@id])[index+1]"));
	return id;
	}	
		

}

