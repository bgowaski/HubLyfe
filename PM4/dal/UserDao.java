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

public class UserDao {
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	//Create User
	public User create(User user) throws SQLException {
		
		String insertUser = "INSERT INTO User(UserName, FirstName, LastName, Password, DateOfBirth, ResidenceZip, OccupationZip, JobTitle) "
				+ "VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);

			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getFirstName());
			insertStmt.setString(3, user.getLastName());
			insertStmt.setString(4, user.getPassword());
			insertStmt.setTimestamp(5, new Timestamp(user.getDob().getTime()));
			insertStmt.setInt(6, user.getResidenceZip());
			insertStmt.setInt(7, user.getOccupationZip());
			insertStmt.setString(8, user.getJobTitle());
			

			insertStmt.executeUpdate();
		
			return user;
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

	//Delete User
	public User delete(User user) throws SQLException {
		String deleteUser = "DELETE FROM User WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + user.getUserName());
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

	//Update LastName
	public User updateLastName(User user, String newLastName) throws SQLException {
		String updateUser = "UPDATE User SET LastName=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newLastName);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setLastName(newLastName);
			return user;
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

	//Update ResidenceZip
	public User updateResidenceZip(User user, int newResidenceZip) throws SQLException {
		String updateUser = "UPDATE User SET ResidenceZip=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setInt(1, newResidenceZip);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setResidenceZip(newResidenceZip);
			return user;
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

	//getUserByUserName
	public User getUserByUserName(String userName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectUser =
			"SELECT  UserName, FirstName, LastName, DateOfBirth, ResidenceZip, OccupationZip, JobTitle " +
			"FROM User " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DateOfBirth").getTime());
				int residenceZip = results.getInt("ResidenceZip");
				int occupationZip = results.getInt("OccupationZip");
				String JobTitle = results.getString("JobTitle");
		
				
				User user = new User(resultUserName,firstName, lastName,null,dob, residenceZip,occupationZip, JobTitle);
				return user;
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
	
	//getUserByFirstName
	public List<User> getUserByFirstName(String firstName) throws SQLException {
		List<User> users = new ArrayList<User>();
		String selectUser =
				"SELECT  UserName, FirstName, LastName, DateOfBirth, ResidenceZip, OccupationZip, JobTitle " +
				"FROM User " +
				"WHERE FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date dob = new Date(results.getTimestamp("DateOfBirth").getTime());
				int residenceZip = results.getInt("ResidenceZip");
				int occupationZip = results.getInt("OccupationZip");
				String JobTitle = results.getString("JobTitle");
				
				User user = new User(userName, resultFirstName, lastName,null,dob, residenceZip,occupationZip, JobTitle);
				 
				users.add(user);
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
		return users;
	}

}
