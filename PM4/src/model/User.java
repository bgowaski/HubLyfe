package model;

import java.util.Date;

public class User {
	
	protected String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getResidenceZip() {
		return residenceZip;
	}

	public void setResidenceZip(int residenceZip) {
		this.residenceZip = residenceZip;
	}

	public int getOccupationZip() {
		return occupationZip;
	}

	public void setOccupationZip(int occupationZip) {
		this.occupationZip = occupationZip;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	protected String firstName;
	protected String lastName;
	protected String password;
	protected Date dob;
	protected int residenceZip;
	protected int occupationZip;
	protected String jobTitle;
	


	public User(String userName, String firstName, String lastName, String password,
			Date dob, int residenceZip, int occupationZip, String jobTitle){
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dob = dob;
		this.residenceZip = residenceZip;
		this.occupationZip = occupationZip;
		this.jobTitle=jobTitle;
	}
	
	public User(String userName){
		this.userName = userName;
	}
	
	public User(String firstName, String lastName, String password,
			Date dob, int residenceZip, int occupationZip,  String jobTitle){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dob = dob;
		this.residenceZip = residenceZip;
		this.occupationZip = occupationZip;
		this.jobTitle = jobTitle;
	}


}
