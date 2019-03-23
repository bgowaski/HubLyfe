package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class SchoolTypeDao {
	
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static SchoolTypeDao instance = null;
	protected SchoolTypeDao() {
		connectionManager = new ConnectionManager();
	}
	public static SchoolTypeDao getInstance() {
		if(instance == null) {
			instance = new SchoolTypeDao();
		}
		return instance;
	}

	// Create SchoolType
	public SchoolType create(SchoolType schoolType) throws SQLException {
		String insertSchoolType =
			"INSERT INTO SchoolType (SchoolId, GradesOffered, SchoolTypology)" + 
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSchoolType,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, schoolType.getSchool().getSchoolId());
			insertStmt.setString(2, schoolType.getGradesOffered());
			insertStmt.setString(3, schoolType.getSchoolTypology().type());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int schoolTypeId = -1;
			if(resultKey.next()) {
				schoolTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			schoolType.setSchoolTypeId(schoolTypeId);
			return schoolType;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	//Delete SchoolType
	public SchoolType delete(SchoolType schoolType) throws SQLException {
		String deleteSchoolType = "DELETE FROM SchoolType WHERE SchoolTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSchoolType);
			deleteStmt.setInt(1, schoolType.getSchoolTypeId());
			deleteStmt.executeUpdate();

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
	

	//getSchoolTypeBySchoolTypeId
	public SchoolType getSchoolTypeBySchoolTypeId(int schoolTypeId) throws SQLException {
		String selectSchoolType =
			"SELECT SchoolTypeId, SchoolId, GradesOffered, SchoolTypology " +
			"FROM SchoolType " +
			"WHERE SchoolTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSchoolType);
			selectStmt.setInt(1, schoolTypeId);
			results = selectStmt.executeQuery();
			PublicSchoolDao publicSchoolDao = PublicSchoolDao.getInstance();
			if(results.next()) {
				int resultSchoolTypeId = results.getInt("SchoolTypeId");
				int schoolId = results.getInt("SchoolId");
				String gradesOffered = results.getString("GradesOffered");
				SchoolType.SchoolTypology schoolTypology = SchoolType.SchoolTypology.get(results.getString("SchoolTypology"));
						
				PublicSchool publicSchool = publicSchoolDao.getPublicSchoolBySchoolId(schoolId);
				SchoolType schoolType = new SchoolType(resultSchoolTypeId, publicSchool, gradesOffered,schoolTypology);
				return schoolType;
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


	//getSchoolTypeBySchoolId
	public List<SchoolType> getSchoolTypeBySchoolId(int schoolId) throws SQLException {
		List<SchoolType> schoolTypes = new ArrayList<SchoolType>();
		String selectSchoolType =
				"SELECT SchoolTypeId, SchoolId, GradesOffered, SchoolTypology " +
				"FROM SchoolType " +
				"WHERE SchoolId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		PublicSchoolDao publicSchoolDao = PublicSchoolDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSchoolType);
			selectStmt.setInt(1, schoolId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int schoolTypeId = results.getInt("SchoolTypeId");
				int resultSchoolId = results.getInt("SchoolId");
				String gradesOffered = results.getString("GradesOffered");
				
				SchoolType.SchoolTypology schoolTypology = SchoolType.SchoolTypology.get(results.getString("SchoolTypology"));
				
				PublicSchool publicSchool = publicSchoolDao.getPublicSchoolBySchoolId(resultSchoolId);
				SchoolType schoolType = new SchoolType(schoolTypeId, publicSchool, gradesOffered,schoolTypology);
				
				schoolTypes.add(schoolType);
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
		return schoolTypes;
	}

	//getSchoolTypeBySchoolTypology
	public List<SchoolType> getSchoolTypeBySchoolTypology(SchoolType.SchoolTypology schoolTypology) throws SQLException {
		List<SchoolType> schoolTypes = new ArrayList<SchoolType>();
		String selectSchoolType =
				"SELECT SchoolTypeId, SchoolId, GradesOffered, SchoolTypology " +
				"FROM SchoolType " +
				"WHERE SchoolTypology=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		PublicSchoolDao publicSchoolDao = PublicSchoolDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSchoolType);
			selectStmt.setString(1, schoolTypology.type());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int schoolTypeId = results.getInt("SchoolTypeId");
				int schoolId = results.getInt("SchoolId");
				String gradesOffered = results.getString("GradesOffered");
				
				SchoolType.SchoolTypology resultSchoolTypology = SchoolType.SchoolTypology.get(results.getString("SchoolTypology"));
				
				PublicSchool publicSchool = publicSchoolDao.getPublicSchoolBySchoolId(schoolId);
				SchoolType schoolType = new SchoolType(schoolTypeId, publicSchool, gradesOffered,resultSchoolTypology);
				
				schoolTypes.add(schoolType);
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
		return schoolTypes;
	}


}
