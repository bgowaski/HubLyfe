package model;

public class JobDetail {

	protected int jobTitleId;
	protected String jobTitle;
	protected JobDepartment department;
	protected double salary;
	protected ZipCode zip;
	
	
	public JobDetail(int jobTitleId, String jobTitle, JobDepartment department,
			double salary, ZipCode zip)
	{
		this.jobTitleId = jobTitleId;
		this.jobTitle = jobTitle;
		this.department = department;
		this.salary = salary;
		this.zip = zip;
	}
	
	public JobDetail(String jobTitle, JobDepartment department,
			double salary, ZipCode zip)
	{
		this.jobTitle = jobTitle;
		this.department = department;
		this.salary = salary;
		this.zip = zip;
	}
	
	public int getJobTitleId() {
		return jobTitleId;
	}

	public void setJobTitleId(int jobTitleId) {
		this.jobTitleId = jobTitleId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public JobDepartment getDepartment() {
		return department;
	}
	
	public String getDepartmentName() {
		return getDepartment().getDepartmentName();
	}

	public void setDepartment(JobDepartment department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public ZipCode getZip() {
		return zip;
	}
	
	public int getZipCode() {
		return getZip().getZip();
	}

	public void setZip(ZipCode zip) {
		this.zip = zip;
	}
	
	
}
