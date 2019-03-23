package model;

public class JobDetail {

	protected int jobTitleId;
	protected String jobTitle;
	protected JobDepartment departmentName;
	protected double salary;
	protected ZipCode zip;
	
	public JobDetail(int jobTitleId, String jobTitle, JobDepartment departmentName,
			double salary, ZipCode zip)
	{
		this.jobTitleId = jobTitleId;
		this.jobTitle = jobTitle;
		this.departmentName = departmentName;
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

	public JobDepartment getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(JobDepartment departmentName) {
		this.departmentName = departmentName;
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

	public void setZip(ZipCode zip) {
		this.zip = zip;
	}
	
	
}
