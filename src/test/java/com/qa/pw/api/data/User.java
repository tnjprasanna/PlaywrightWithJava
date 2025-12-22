package com.qa.pw.api.data;

public class User {
	
	private String id;
	private String FirstName;
	private String email;
	private String location;
	private String phoneNum;
	private int age;
	private int salary;
	
	public User() {}
	
	public User(String id, String FirstName, String email, String location, String phoneNum, int age, int salary ) {
		
		this.id = id;
		this.FirstName = FirstName;
		this.email = email;
		this.location = location;
		this.phoneNum = phoneNum;
		this.age = age;
		this.salary = salary;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User{" +
		", id='" + id +'\'' +
		", FirstName='" + FirstName +'\'' +
		", email='" + email +'\'' +
		", location='" + location +'\'' +
		", phoneNum='" + phoneNum +'\'' +
		", age='" + age +'\'' +
		", salary='" + salary +'\'' +
		'}';
	}
	
}
