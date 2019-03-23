package model;


public class PublicSchool {
	
	protected int schoolId;
	protected String schoolName;
	protected String historicalName;
	protected String abbreviatedName;
	protected String address;
	protected String educationProgram;
	protected int yearFounded;
	protected int yearBuilt;
	protected String schoolOpen;
	protected String schoolClose;
	protected String schoolEnrollment;
	protected Neighborhood neighborhood;
	
	public PublicSchool(int schoolId,String schoolName,String historicalName,String abbreviatedName,
			String address,String educationProgram, int yearFounded, int yearBuilt,
		    String schoolOpen,String schoolClose,String schoolEnrollment, Neighborhood neighborhood) {

		this.schoolId = schoolId;
		this.schoolName = schoolName;
		this.historicalName = historicalName;
		this.abbreviatedName = abbreviatedName;
		this.address = address;
		this.educationProgram = educationProgram;
		this.yearFounded = yearFounded;
		this.yearBuilt = yearBuilt;
		this.schoolOpen = schoolOpen;
		this.schoolClose = schoolClose;
		this.schoolEnrollment = schoolEnrollment;
		this.neighborhood = neighborhood;
	}

	public PublicSchool(int schoolId) {
		this.schoolId = schoolId;
	}

	public PublicSchool(String schoolName,String historicalName,String abbreviatedName,
			String address,String educationProgram, int yearFounded, int yearBuilt,
		    String schoolOpen,String schoolClose,String schoolEnrollment, Neighborhood neighborhood) {

		this.schoolName = schoolName;
		this.historicalName = historicalName;
		this.abbreviatedName = abbreviatedName;
		this.address = address;
		this.educationProgram = educationProgram;
		this.yearFounded = yearFounded;
		this.yearBuilt = yearBuilt;
		this.schoolOpen = schoolOpen;
		this.schoolClose = schoolClose;
		this.schoolEnrollment = schoolEnrollment;
		this.neighborhood = neighborhood;
	}
	
	
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getHistoricalName() {
		return historicalName;
	}
	public void setHistoricalName(String historicalName) {
		this.historicalName = historicalName;
	}
	public String getAbbreviatedName() {
		return abbreviatedName;
	}
	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEducationProgram() {
		return educationProgram;
	}
	public void setEducationProgram(String educationProgram) {
		this.educationProgram = educationProgram;
	}
	public int getYearFounded() {
		return yearFounded;
	}
	public void setYearFounded(int yearFounded) {
		this.yearFounded = yearFounded;
	}
	public int getYearBuilt() {
		return yearBuilt;
	}
	public void setYearBuilt(int yearBuilt) {
		this.yearBuilt = yearBuilt;
	}
	public String getSchoolOpen() {
		return schoolOpen;
	}
	public void setSchoolOpen(String schoolOpen) {
		this.schoolOpen = schoolOpen;
	}
	public String getSchoolClose() {
		return schoolClose;
	}
	public void setSchoolClose(String schoolClose) {
		this.schoolClose = schoolClose;
	}
	public String getSchoolEnrollment() {
		return schoolEnrollment;
	}
	public void setSchoolEnrollment(String schoolEnrollment) {
		this.schoolEnrollment = schoolEnrollment;
	}
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}


}
