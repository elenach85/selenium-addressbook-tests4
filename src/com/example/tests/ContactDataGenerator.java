package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length<3){
		System.out.println("Please specify parameters: <amount of test data> <file> <format>");
		return;
	}
	 int amount=Integer.parseInt(args[0]);
	 File file=new File(args[1]);
	 String format=args[2];
	 if (file.exists())	 {
		 System.out.println("File exists,please remove it manually:"+ file);
			return;
	 }
	 
	 List<ContactData>contacts=generateRandomContacts(amount);
	 if ("csv".equals(format)){
		 saveContactsToCsvFile(contacts,file);
	 }
	 else if("xml".equals(format)){
		 saveContactsToXmlFile(contacts,file);
	 }
	 else {
		 System.out.println("Unknown file format"+ format);	
return;
	 }
	
}

	private static void saveContactsToXmlFile(List<ContactData> contacts, File file) throws IOException {
	XStream xstream=new XStream();	
	xstream.alias("contact", ContactData.class);
	String xml = xstream.toXML(contacts);
	FileWriter writer=new FileWriter(file);
	writer.write(xml);
	writer.close();
	}
	public static List<ContactData> loadContactsFromXmlFile(File file) {
		XStream xstream=new XStream();	
		xstream.alias("contact", ContactData.class);
		return (List<ContactData>) xstream.fromXML(file);
	}
	private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer=new FileWriter(file);
		for (ContactData contact: contacts) {
		writer.write(contact.getFirst_name()+"," + contact.getLast_name()+","+ contact.getAddress_1()+"," + contact.getHome_tel()+"," + contact.getMobile_tel()+"," + contact.getWork_tel()+"," + contact.getEmail()+"," + contact.getEmail2()+"," + contact.getBirth_day()+"," + contact.getBirth_month()+"," + contact.getBirth_year()+"," + contact.getAddress_2()+"," + contact.getPhone_2()+",!"+"\n");	 
		}
		writer.close();
		
	}
	public static List<ContactData> loadContactsFromCsvFile(File file) throws IOException  {
		List<ContactData>list=new ArrayList<ContactData>();
		FileReader reader=new FileReader(file);
		BufferedReader bufferedReader=new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line!=null ) {
		String part[]=line.split(",");
		ContactData contact=new ContactData()
				.withFirstname(part[0])
				.withLastname(part[1])
				.withAddress1(part[2])
				.withHomeTel(part[3])
				.withMobile_Tel(part[4])
				.withWorktel(part[5])
				.withEmail(part[6])
				.withEmail2(part[7])
				.withBirthDay(part[8])
				.withBirthMonth(part[9])
				.withBirthYear(part[10])
				.withEmail2(part[11])
				.withPhone2(part[12]);		
		list.add(contact);
		line= bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}
	public static List<ContactData> generateRandomContacts(int amount) {
			List<ContactData>list=new ArrayList<ContactData>();
				for (int i = 0; i <amount; i++) {
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
			list.add(contact);
			}
			return list;

	}
	public static  String generateRandomString(){
		Random rnd=new Random(); 
		if (rnd.nextInt(3)==0) {
			return " ";	
			} else {
			return "test"+ rnd.nextInt();
			}	
		
}
	public static String generateRandomYear() {
		Random rnd=new Random(); 
		if (rnd.nextInt(7)==0) {
			return " ";	
			} else {
				String year;
				year=String.valueOf(1920+rnd.nextInt(95));
			return year;
			}	
	}
	
public static String generateRandomMonth() {
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
protected static String generateRandomDay(){
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
}
	

