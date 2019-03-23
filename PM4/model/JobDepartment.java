package model;

public class JobDepartment {
	protected String departmentName;
	
	public JobDepartment(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
