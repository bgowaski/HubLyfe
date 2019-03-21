package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class EthnicityDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static EthnicityDao instance = null;
	protected EthnicityDao() {
		connectionManager = new ConnectionManager();
	}
	public static EthnicityDao getInstance() {
		if(instance == null) {
			instance = new EthnicityDao();
		}
		return instance;
	}

	// Create Ethnicity
	public Ethnicity create(Ethnicity ethnicity) throws SQLException {
		String insertEthnicity =
			"INSERT INTO Ethnicity (DemographicId, EthnicityType, EthnicityPopulation)" + 
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEthnicity,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, ethnicity.getDemographic().getDemographicId());
			insertStmt.setString(2, ethnicity.getEthnicityType().value());
			insertStmt.setInt(3, ethnicity.getEthnicityPopulation());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ethnicityId = -1;
			if(resultKey.next()) {
				ethnicityId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ethnicity.setEthnicityId(ethnicityId);
			return ethnicity;
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
	
	//Delete Ethnicity
	public Ethnicity delete(Ethnicity ethnicity) throws SQLException {
		String deleteSchoolType = "DELETE FROM Ethnicity WHERE EthnicityId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSchoolType);
			deleteStmt.setInt(1, ethnicity.getEthnicityId());
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
	

	//getEthnicityByEthnicityId
	public Ethnicity getEthnicityByEthnicityId(int ethnicityId) throws SQLException {
		String selectEthnicity =
			"SELECT EthnicityId, DemographicId, EthnicityType, EthnicityPopulation\n " +
			"FROM Ethnicity " +
			"WHERE EthnicityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEthnicity);
			selectStmt.setInt(1, ethnicityId);
			results = selectStmt.executeQuery();
			DemographicDao demographicDao = DemographicDao.getInstance();
			if(results.next()) {
				int resultEthnicityId = results.getInt("EthnicityId");
				int demographicId = results.getInt("DemographicId");
				Ethnicity.EthnicityType ethnicityType = Ethnicity.EthnicityType.get(results.getString("EthnicityType"));
				int ethnicityPopulation = results.getInt("ethnicityPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(demographicId);
				Ethnicity ethnicity = new Ethnicity(resultEthnicityId, demographic, ethnicityType,ethnicityPopulation);
				return ethnicity;
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
	public List<Ethnicity> getEthnicityByDemographicId(int demographicId) throws SQLException {
		List<Ethnicity> ethnicities = new ArrayList<Ethnicity>();
		String selectEthnicity =
				"SELECT EthnicityId, DemographicId, EthnicityType, EthnicityPopulation\n " +
				"FROM Ethnicity " +
				"WHERE DemographicId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		DemographicDao demographicDao = DemographicDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEthnicity);
			selectStmt.setInt(1, demographicId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ethnicityId = results.getInt("EthnicityId");
				int resultDemograhicId = results.getInt("DemographicId");
				Ethnicity.EthnicityType ethnicityType = Ethnicity.EthnicityType.get(results.getString("EthnicityType"));
				int ethnicityPopulation = results.getInt("ethnicityPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(resultDemograhicId);
				Ethnicity ethnicity = new Ethnicity(ethnicityId, demographic, ethnicityType,ethnicityPopulation);
				ethnicities.add(ethnicity);
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
		return ethnicities;
	}

	//getEthnicityByEthnicityType
	public List<Ethnicity> getEthnicityByEthnicityType(Ethnicity.EthnicityType ethnicityType) throws SQLException {
		List<Ethnicity> ethnicities = new ArrayList<Ethnicity>();
		String selectEthnicity =
				"SELECT EthnicityId, DemographicId, EthnicityType, EthnicityPopulation\n " +
				"FROM Ethnicity " +
				"WHERE EthnicityType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		DemographicDao demographicDao = DemographicDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEthnicity);
			selectStmt.setString(1, ethnicityType.value());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int ethnicityId = results.getInt("EthnicityId");
				int demograhicId = results.getInt("DemographicId");
				Ethnicity.EthnicityType resultEthnicityType = Ethnicity.EthnicityType.get(results.getString("EthnicityType"));
				int ethnicityPopulation = results.getInt("ethnicityPopulation");
						
				Demographic demographic = demographicDao.getDemographicByDemographicId(demograhicId);
				Ethnicity ethnicity = new Ethnicity(ethnicityId, demographic, resultEthnicityType,ethnicityPopulation);
				ethnicities.add(ethnicity);
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
		return ethnicities;
	}

	
}
