package com.example.tests;

public class ContactData implements Comparable<ContactData> {
	private String first_name;
	private String last_name;
	private String address_1;
	private String home_tel;
	private String mobile_tel;
	private String work_tel;
	private String email;
	private String email2;
	String birth_day;
	String birth_month;
	String birth_year;
	public String group_name;
	private String address_2;
	private String phone_2;
	//private String group_name;
	private String contact_id;

	public ContactData(String first_name, String last_name, String address_1, String home_tel, String mobile_tel,
			String work_tel, String email,String email2, String birth_day, String birth_month, String birth_year, String group_name,
			String address_2, String phone_2,String id) {
		this.first_name = first_name;
		this.last_name=last_name;
		this.address_1 = address_1;
		this.home_tel = home_tel;
		this.mobile_tel = mobile_tel;
		this.work_tel = work_tel;
		this.email = email;
		this.birth_day = birth_day;
		this.birth_month = birth_month;
		this.birth_year = birth_year;
		this.group_name = group_name;
		this.address_2 = address_2;
		this.phone_2 = phone_2;
		this.contact_id=id;
	}
	public ContactData(){
		
	}

	

	
	@Override
	public String toString() {
		return "ContactData [first_name=" + first_name + ", last_name=" + last_name + ", home_tel=" + home_tel
				+ ", email=" + email + ", id=" + contact_id + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((home_tel == null) ? 0 : home_tel.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((contact_id == null) ? 0 : contact_id.hashCode());
		return result;
	}
	
@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;

	 if (contact_id != null && other.contact_id != null) {
			  if (! contact_id.equals(other.contact_id)) {
			    return false;
			  }
			}
			
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.trim().equals(other.email.trim()))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.trim().equals(other.first_name.trim()))
			return false;
		if (home_tel == null) {
			if (other.home_tel != null)
				return false;
		} else if (!home_tel.trim().equals(other.home_tel.trim()))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.trim().equals(other.last_name.trim()))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(ContactData other) {
	if (this.contact_id == null) return 1;
		if (other.contact_id == null) return -1;
		return Integer.valueOf(this.contact_id).compareTo(Integer.valueOf(other.contact_id));
		
	//return this.last_name.toLowerCase().compareTo(other.last_name.toLowerCase());
	}
	public ContactData withFirstname(String first_name) {
		this.first_name = first_name;
		return this;
	}
	public ContactData withLastname(String last_name) {
		this.last_name = last_name;
		return this;
	}
	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}
	public ContactData withEmail2(String email2) {
		this.email2= email2;
		return this;
	}	
	public ContactData withHomeTel(String home_tel) {
		this.home_tel = home_tel;
		return this;
	}	
	public ContactData withAddress1(String address_1) {
		address_1 = address_1;
		return this;
	}
	public ContactData withPhone2(String phone_2) {
		this.phone_2 = phone_2;
		return this;
	}
	public ContactData withWorktel(String worktel) {
		work_tel = worktel;
		return this;
	}
	public ContactData withBirthYear(String birthyear) {
		birth_year = birthyear;
		return this;
	}
	public ContactData withBirthMonth(String birthmonth) {
		birth_month = birthmonth;
		return this;
	}
	public ContactData withBirthDay(String birthday) {
		birth_day = birthday;
		return this;
	}
	public ContactData withGroupName(String groupname) {
		group_name = groupname;
		return this;
	}
	public ContactData withId(String id) {
         contact_id=id;
		return this;
	}

	public String getFirst_name() {
		return first_name;
	}
	
	public String getLast_name() {
		return last_name;
	}
	public String getAddress_1() {
		return address_1;
	}
	
	public String getHome_tel() {
		return home_tel;
	}
	
	public String getMobile_tel() {
		return mobile_tel;
	}
	
	public String getWork_tel() {
		return work_tel;
	}
	
	public String getEmail() {
		return email;
	}

	public String getEmail2() {
		return email2;
	}
	
	public String getBirth_day() {
		return birth_day;
	}
	public String getBirth_month() {
		return birth_month;
	}

	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getGroup_name() {
		return group_name;
	}
	
	public String getAddress_2() {
		return address_2;
	}
	
	public String getPhone_2() {
		return phone_2;
	}
	public String getId() {
		return contact_id;
	}
	
	public void setId(String contact_id) {
		this.contact_id = contact_id;
	}
	}
