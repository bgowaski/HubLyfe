package model;

public class User {
	
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String password;
	protected int dob;
	protected ZipCode residenceZip;
	protected ZipCode occupationZip;
	
	public User(String userName, String firstName, String lastName, String password,
			int dob, ZipCode residenceZip, ZipCode occupationZip)
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dob = dob;
		this.residenceZip = residenceZip;
		this.occupationZip = occupationZip;
	}

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

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

	public ZipCode getResidenceZip() {
		return residenceZip;
	}

	public void setResidenceZip(ZipCode residenceZip) {
		this.residenceZip = residenceZip;
	}

	public ZipCode getOccupationZip() {
		return occupationZip;
	}

	public void setOccupationZip(ZipCode occupationZip) {
		this.occupationZip = occupationZip;
	}
	
	
}
