package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;

public class JobDetailDao {
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static JobDetailDao instance = null;
	protected JobDetailDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobDetailDao getInstance() {
		if(instance == null) {
			instance = new JobDetailDao();
		}
		return instance;
	}

	//Create JobDetail
	public JobDetail create(JobDetail jobDetail) throws SQLException {
		
		String insertUser = "INSERT INTO JobDetail(JobTitleId, JobTitle, DepartmentName, Salary, Zip) "
				+ "VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);

			insertStmt.setInt(1, jobDetail.getJobTitleId());
			insertStmt.setString(2, jobDetail.getJobTitle());
			insertStmt.setString(3, jobDetail.getDepartmentName().getDepartmentName());
			insertStmt.setDouble(4, jobDetail.getSalary());
			insertStmt.setInt(5, jobDetail.getZip().getZip());
			

			insertStmt.executeUpdate();
		
			return jobDetail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	//Delete JobDetail
	public JobDetail delete(JobDetail jobDetail) throws SQLException {
		String deleteUser = "DELETE FROM JobDetail WHERE JobTitleId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, jobDetail.getJobTitleId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for JobDetail=" + jobDetail.getJobTitleId());
			}

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	//Update Salary
	public JobDetail updateSalary(JobDetail jobDetail, int newSalary) throws SQLException {
		String updateUser = "UPDATE JobDetail SET Salary=? WHERE JobDetail=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setDouble(1, newSalary);
			updateStmt.setDouble(2, jobDetail.getSalary());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			jobDetail.setSalary(newSalary);
			return jobDetail;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	//getJobDetailByJobTitleId
	public JobDetail getJobDetailByJobTitleId(int jobTitleId) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectJobDetail=
			"SELECT  JobTitleId,JobTitle,DepartmentName,Salary,Zip" +
			"FROM JobDetail " +
			"WHERE JobTitleId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobDetail);
			selectStmt.setInt(1, jobTitleId);
			results = selectStmt.executeQuery();
			JobDepartmentDao jobDepartmentDao = JobDepartmentDao.getInstance();
			ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
			if(results.next()) {
				int resultJobTitleId = results.getInt("JobTitleId");
				String jobTitle = results.getString("JobTitle");
				String departmentName = results.getString("DepartmentName");
				double salary = results.getDouble("Salary");
				int zip = results.getInt("Zip");
		
				JobDepartment jobDepartment = jobDepartmentDao.getJobDepartmentByDepartmentName(departmentName);
				ZipCode zipCode = zipCodeDao.getZipCodeByZip(zip);
				JobDetail jobDetail = new JobDetail(resultJobTitleId,jobTitle, jobDepartment,salary,zipCode);
				return jobDetail;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	//getJobDetailByZipCode
		public List<JobDetail> getJobDetailByZipCode(int zipCode) throws SQLException {
			List<JobDetail> jobDetails = new ArrayList<JobDetail>();
			String selectJobDetail =
					"SELECT  JobTitle,DepartmentName,Salary" +
					"FROM JobDetail " +
					"WHERE ZipCode=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectJobDetail);
				selectStmt.setString(1, zipCode);
				results = selectStmt.executeQuery();
				ZipCodeDao ZipCodeDao = ZipCodeDao.getInstance();
				ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
				while(results.next()) {
					String jobTitle = results.getString("JobTitle");
					String departmentName = results.getString("DepartmentName");
					double salary = results.getDouble("Salary");
			
					ZipCode zipCode = zipCodeDao.getZipCodeByZip(zip);
					JobDetail jobDetail = new JobDetail(jobTitle, departmentName,salary);
					jobDetails.add(jobDetail);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return jobDetails;
		}	
	
	//getJobDetailByDepartmentName
	public List<JobDetail> getJobDetailByDepartmentName(String departmentName) throws SQLException {
		List<JobDetail> jobDetails = new ArrayList<JobDetail>();
		String selectJobDetail =
				"SELECT  JobTitleId,JobTitle,DepartmentName,Salary,Zip" +
				"FROM JobDetail " +
				"WHERE DepartmentName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobDetail);
			selectStmt.setString(1, departmentName);
			results = selectStmt.executeQuery();
			JobDepartmentDao jobDepartmentDao = JobDepartmentDao.getInstance();
			ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
			while(results.next()) {
				int jobTitleId = results.getInt("JobTitleId");
				String jobTitle = results.getString("JobTitle");
				String resultDepartmentName = results.getString("DepartmentName");
				double salary = results.getDouble("Salary");
				int zip = results.getInt("Zip");
		
				JobDepartment jobDepartment = jobDepartmentDao.getJobDepartmentByDepartmentName(resultDepartmentName);
				ZipCode zipCode = zipCodeDao.getZipCodeByZip(zip);
				JobDetail jobDetail = new JobDetail(jobTitleId,jobTitle, jobDepartment,salary,zipCode);
				jobDetails.add(jobDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return jobDetails;
	}
	//getJobDetailByPartialTitle
	public List<JobDetail> getJobDetailByPartialTitle(String partialTitle) throws SQLException {
		List<JobDetail> jobDetails = new ArrayList<JobDetail>();
		String selectJobDetail =
				"SELECT JobTitleId,JobTitle,DepartmentName,Salary,Zip\n" +
				"FROM JobDetail " +
				"WHERE JobTitle like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobDetail);
			selectStmt.setString(1, "%" + partialTitle + "%");
			results = selectStmt.executeQuery();
			JobDepartmentDao jobDepartmentDao = JobDepartmentDao.getInstance();
			ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
			while(results.next()) {
				int jobTitleId = results.getInt("JobTitleId");
				String jobTitle = results.getString("JobTitle");
				String resultDepartmentName = results.getString("DepartmentName");
				double salary = results.getDouble("Salary");
				int zip = results.getInt("Zip");
		
				JobDepartment jobDepartment = jobDepartmentDao.getJobDepartmentByDepartmentName(resultDepartmentName);
				ZipCode zipCode = zipCodeDao.getZipCodeByZip(zip);
				JobDetail jobDetail = new JobDetail(jobTitleId, jobTitle, jobDepartment,salary,zipCode);
				jobDetails.add(jobDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return jobDetails;
	}

}
