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

public class JobDepartmentDao {
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static JobDepartmentDao instance = null;
	protected JobDepartmentDao() {
		connectionManager = new ConnectionManager();
	}
	public static JobDepartmentDao getInstance() {
		if(instance == null) {
			instance = new JobDepartmentDao();
		}
		return instance;
	}

	//Create JobDepartment
	public JobDepartment create(JobDepartment jobDepartment) throws SQLException {
		
		String insertJobDepartment = "INSERT INTO JobDepartment(DepartmentName) "
				+ "VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertJobDepartment);

			insertStmt.setString(1, jobDepartment.getDepartmentName());
			

			insertStmt.executeUpdate();
		
			return jobDepartment;
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

	//Delete JobDepartment
	public JobDepartment delete(JobDepartment jobDepartment) throws SQLException {
		String deleteJobDepartment = "DELETE FROM JobDepartment WHERE DepartmentName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteJobDepartment);
			deleteStmt.setString(1, jobDepartment.getDepartmentName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for JobDepartment=" + jobDepartment.getDepartmentName());
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

	//getJobDepartmentByDepartmentName
	public JobDepartment getJobDepartmentByDepartmentName(String departmentName) throws SQLException {
		String selectJobDepartment =
			"SELECT DepartmentName " +
			"FROM JobDepartment " +
			"WHERE DepartmentName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectJobDepartment);
			selectStmt.setString(1, departmentName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultDepartmentName= results.getString("DepartmentName");				
				JobDepartment jobDepartment = new JobDepartment(resultDepartmentName);
				return jobDepartment;
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
	

}
