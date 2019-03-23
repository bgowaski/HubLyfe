package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class EducationalAttainmentDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static EducationalAttainmentDao instance = null;
	protected EducationalAttainmentDao() {
		connectionManager = new ConnectionManager();
	}
	public static EducationalAttainmentDao getInstance() {
		if(instance == null) {
			instance = new EducationalAttainmentDao();
		}
		return instance;
	}

	// Create EducationalAttainment
	public EducationalAttainment create(EducationalAttainment educationalAttainment) throws SQLException {
		String insertEducationalAttainment =
			"INSERT INTO EducationalAttainment (DemographicId,EdType,EdPopulation)" + 
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEducationalAttainment,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, educationalAttainment.getDemographicId().getDemographicId());
			insertStmt.setString(2, educationalAttainment.getEdType().value());
			insertStmt.setInt(3, educationalAttainment.getEdPopulation());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int edAttainmentId = -1;
			if(resultKey.next()) {
				edAttainmentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			educationalAttainment.setEdAttainmentId(edAttainmentId);
			return educationalAttainment;
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
	
	//Delete EducationalAttainment
	public EducationalAttainment delete(EducationalAttainment educationalAttainment) throws SQLException {
		String deleteSchoolType = "DELETE FROM EducationalAttainment WHERE EducationalAttainmentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSchoolType);
			deleteStmt.setInt(1, educationalAttainment.getEdAttainmentId());
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
	

	//getEducationalAttainmentByEducationalAttainmentId
	public EducationalAttainment getEdAttainmentByEdAttainmentId(int edAttainmentId) throws SQLException {
		String selectEducationalAttainment =
			"SELECT EdAttainmentId, DemographicId, EdType, EdPopulation\n " +
			"FROM EducationalAttainment " +
			"WHERE EdAttainmentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEducationalAttainment);
			selectStmt.setInt(1, edAttainmentId);
			results = selectStmt.executeQuery();
			DemographicDao demographicDao = DemographicDao.getInstance();
			if(results.next()) {
				int resultEducationalAttainmentId = results.getInt("EdAttainmentId");
				int demographicId = results.getInt("DemographicId");
				EducationalAttainment.EdType edType = EducationalAttainment.EdType.get(results.getString("EdType"));
				int edPopulation = results.getInt("EdPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(demographicId);
				EducationalAttainment educationalAttainment = new EducationalAttainment(resultEducationalAttainmentId, demographic, edType,edPopulation);
				return educationalAttainment;
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


	//getEthnicityByDemographicId
	public List<EducationalAttainment> getEdAttainmentByDemographicId(int demographicId) throws SQLException {
		List<EducationalAttainment> edAttainments = new ArrayList<EducationalAttainment>();
		String selectEducationalAttainment =
				"SELECT EdAttainmentId, DemographicId, EdType, EdPopulation\n " +
				"FROM EducationalAttainment " +
				"WHERE DemographicId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		DemographicDao demographicDao = DemographicDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEducationalAttainment);
			selectStmt.setInt(1, demographicId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int edAttainmentId = results.getInt("EdAttainmentId");
				int resultDemograhicId = results.getInt("DemographicId");
				EducationalAttainment.EdType edType = EducationalAttainment.EdType.get(results.getString("EdType"));
				int edPopulation = results.getInt("EdPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(resultDemograhicId);
				EducationalAttainment edAttainment = new EducationalAttainment(edAttainmentId, demographic,edType,edPopulation);
				edAttainments.add(edAttainment);
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
		return edAttainments;
	}

	//getEthnicityByEthnicityType
	public List<EducationalAttainment> getEdAttainmentByEdType(EducationalAttainment.EdType edType) throws SQLException {
		List<EducationalAttainment> edAttainments = new ArrayList<EducationalAttainment>();
		String selectEdAttainment =
				"SELECT EdAttainmentId, DemographicId, EdType, EdPopulation\n " +
				"FROM EducationalAttainment " +
				"WHERE EdType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		DemographicDao demographicDao = DemographicDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEdAttainment);
			selectStmt.setString(1, edType.value());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int edAttainmentId = results.getInt("EdAttainmentId");
				int resultDemograhicId = results.getInt("DemographicId");
				EducationalAttainment.EdType resultEdType = EducationalAttainment.EdType.get(results.getString("EdType"));
				int edPopulation = results.getInt("EdPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(resultDemograhicId);
				EducationalAttainment edAttainment = new EducationalAttainment(edAttainmentId, demographic,resultEdType,edPopulation);
				edAttainments.add(edAttainment);
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
		return edAttainments;
	}

	
}
