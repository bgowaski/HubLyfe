package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class AgeDataDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static AgeDataDao instance = null;
	protected AgeDataDao() {
		connectionManager = new ConnectionManager();
	}
	public static AgeDataDao getInstance() {
		if(instance == null) {
			instance = new AgeDataDao();
		}
		return instance;
	}

	// Create AgeData
	public AgeData create(AgeData ageData) throws SQLException {
		String insertAgeData =
			"INSERT INTO AgeData (DemographicId, AgeRange, AgePercentage)" + 
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAgeData,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, ageData.getDemographicId().getDemographicId());
			insertStmt.setString(2, ageData.getAgeRange().value());
			insertStmt.setInt(3, ageData.getAgePercentage());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ageId = -1;
			if(resultKey.next()) {
				ageId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ageData.setAgeId(ageId);
	
			return ageData;
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

	//Delete AgeData
	public AgeData delete(AgeData ageData) throws SQLException {
		String deleteAgeData = "DELETE FROM AgeData WHERE AgeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAgeData);
			deleteStmt.setInt(1, ageData.getAgeId());
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

	//getAgeDataByAgeId
	public AgeData getAgeDataByAgeId(int ageId) throws SQLException {
		String selectAgeData =
			"SELECT AgeId, DemographicId, AgeRange, AgePercentage" +
			"FROM AgeData " +
			"WHERE AgeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAgeData);
			selectStmt.setInt(1, ageId);
			results = selectStmt.executeQuery();
			DemographicDao demographicDao = DemographicDao.getInstance();
			if(results.next()) {
				int resultAgeId = results.getInt("AgeId");
				int demographicId = results.getInt("DemographicId");
				AgeData.AgeRange ageRange = AgeData.AgeRange.get(results.getString("AgeRange"));
				int agePercentage = results.getInt("AgePercentage");
				
				Demographic demographic = demographicDao.getDemographicByDemographicId(demographicId);
				AgeData ageData = new AgeData(resultAgeId, demographic, ageRange, agePercentage);
				return ageData;
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
	
	//getAgeDatabyAgeRange
	public List<AgeData> getAgeDatabyAgeRange(AgeData.AgeRange ageRange) throws SQLException {
		List<AgeData> ageDatas = new ArrayList<AgeData>();
		String selectAgeData =
				"SELECT AgeId, DemographicId, AgeRange, AgePercentage " +
				"FROM AgeData " +
				"WHERE AgeRange=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectAgeData);
				selectStmt.setString(1, ageRange.value());
				results = selectStmt.executeQuery();
				DemographicDao demographicDao = DemographicDao.getInstance();
				if(results.next()) {
					int resultAgeId = results.getInt("AgeId");
					int demographicId = results.getInt("DemographicId");
					AgeData.AgeRange resultAgeRange = AgeData.AgeRange.get(results.getString("AgeRange"));
					int agePercentage = results.getInt("AgePercentage");
					
					Demographic demographic = demographicDao.getDemographicByDemographicId(demographicId);
					AgeData ageData = new AgeData(resultAgeId, demographic, resultAgeRange, agePercentage);
					ageDatas.add(ageData);
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
		return ageDatas;
	}

	


	
}
