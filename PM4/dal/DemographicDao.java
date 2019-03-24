package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.AgeData.AgeRange;
import model.EducationalAttainment.EdType;
import model.Ethnicity.EthnicityType;

public class DemographicDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static DemographicDao instance = null;
	protected DemographicDao() {
		connectionManager = new ConnectionManager();
	}
	public static DemographicDao getInstance() {
		if(instance == null) {
			instance = new DemographicDao();
		}
		return instance;
	}

	// Create Demographic
	public Demographic create(Demographic demographic) throws SQLException {
		String insertDemographic =
			"INSERT INTO Demographic (Population, ForiegnBorn, FemaleLaborForce, MaleLaborForce" + 
			"			,OccupiedHousingUnits, OwnerOccupiedUnits, RenterOccupiedUnits, NeighborhoodName)" + 
			"VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDemographic,
				Statement.RETURN_GENERATED_KEYS);
	
			insertStmt.setDouble(1, demographic.getPopulation());
			insertStmt.setInt(2, demographic.getForiegnBorn());
			insertStmt.setInt(3, demographic.getFemaleLaborForce());
			insertStmt.setInt(4, demographic.getMaleLaborForce());
			insertStmt.setInt(5, demographic.getOccupiedHousingUnits());
			insertStmt.setInt(6, demographic.getOwnerOccupiedUnits());
			insertStmt.setInt(7, demographic.getRenterOccupiedUnits());
			insertStmt.setString(8, demographic.getNeighborhood().getNeighborhoodName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int demographicId = -1;
			if(resultKey.next()) {
				demographicId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			demographic.setDemographicId(demographicId);
			return demographic;
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

	//Delete Demographic
	public Demographic delete(Demographic demographic) throws SQLException {
		String deleteDemographic = "DELETE FROM Demographic WHERE DemographicId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDemographic);
			deleteStmt.setInt(1, demographic.getDemographicId());
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

	//getDemographicByDemographicId
	public Demographic getDemographicByDemographicId(int demographicId) throws SQLException {
		String selectDemographic =
			"SELECT DemographicId, Population, ForiegnBorn, FemaleLaborForce, MaleLaborForce" + 
			",OccupiedHousingUnits, OwnerOccupiedUnits, RenterOccupiedUnits, NeighborhoodName\n" +
			"FROM Demographic " +
			"WHERE DemographicId=?;";
	
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDemographic);
			
			selectStmt.setInt(1, demographicId);
			
			results = selectStmt.executeQuery();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			if(results.next()) {
				int resultDemographicId = results.getInt("DemographicId");
				double population = results.getDouble("Population");
				int foriegnBorn = results.getInt("ForiegnBorn");
				int femaleLaborForce = results.getInt("FemaleLaborForce");
				int maleLaborForce = results.getInt("MaleLaborForce");
				int occupiedHousingUnits = results.getInt("OccupiedHousingUnits");
				int ownerOccupiedUnits = results.getInt("OwnerOccupiedUnits");
				int renterOccupiedUnits = results.getInt("RenterOccupiedUnits");
				String neighborhoodName = results.getString("NeighborhoodName");
				
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(neighborhoodName);
				Demographic demographic = new Demographic(resultDemographicId,population, foriegnBorn,
						femaleLaborForce, maleLaborForce, occupiedHousingUnits, ownerOccupiedUnits,renterOccupiedUnits, 
						 neighborhood);
				return demographic;
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
	
	//getDemographicByNeighborhoodName
	public List<Demographic> getDemographicByNeighborhoodName(String neighborhoodName) throws SQLException {
		List<Demographic> demographics = new ArrayList<Demographic>();
		String selectDemographic =
				"SELECT DemographicId, Population, ForiegnBorn, FemaleLaborForce, MaleLaborForce" + 
				",OccupiedHousingUnits, OwnerOccupiedUnits, RenterOccupiedUnits, NeighborhoodName\n" +
				"FROM Demographic " +
				"WHERE NeighborhoodName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectDemographic);
				selectStmt.setString(1, neighborhoodName);
				results = selectStmt.executeQuery();
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				while(results.next()) {
					int demographicId = results.getInt("DemographicId");
					double population = results.getDouble("Population");
					int foriegnBorn = results.getInt("ForiegnBorn");
					int femaleLaborForce = results.getInt("FemaleLaborForce");
					int maleLaborForce = results.getInt("MaleLaborForce");
					int occupiedHousingUnits = results.getInt("OccupiedHousingUnits");
					int ownerOccupiedUnits = results.getInt("OwnerOccupiedUnits");
					int renterOccupiedUnits = results.getInt("RenterOccupiedUnits");
					String resultNeighborhoodName = results.getString("NeighborhoodName");
					
					Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
					Demographic demographic = new Demographic(demographicId,population, foriegnBorn,
							femaleLaborForce, maleLaborForce, occupiedHousingUnits, ownerOccupiedUnits,renterOccupiedUnits, 
							 neighborhood);
					demographics.add(demographic);
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
		return demographics;
	}

	//getDemographicByNeighborhoodName
		public List<Demographic> getAllDemographicByNeighborhoodName(String neighborhoodName) throws SQLException {
			List<Demographic> demographics = new ArrayList<Demographic>();
			String selectDemographic =
					"SELECT Demographic.DemographicId AS DemographicId , Population, ForiegnBorn, FemaleLaborForce, MaleLaborForce" + 
					",OccupiedHousingUnits, OwnerOccupiedUnits, RenterOccupiedUnits, NeighborhoodName," +
					" EdAttainmentId, EdType, EdPopulation\n"+
					"FROM Demographic\n " +
					"INNER JOIN EducationalAttainment ON EducationalAttainment.DemographicId = Demographic.DemographicId\n " +
					"WHERE NeighborhoodName=?;";
				Connection connection = null;
				PreparedStatement selectStmt = null;
				ResultSet results = null;
				try {
					connection = connectionManager.getConnection();
					selectStmt = connection.prepareStatement(selectDemographic);
					selectStmt.setString(1, neighborhoodName);
					results = selectStmt.executeQuery();
					NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
					while(results.next()) {
						int demographicId = results.getInt("DemographicId");
						double population = results.getDouble("Population");
						int foriegnBorn = results.getInt("ForiegnBorn");
						int femaleLaborForce = results.getInt("FemaleLaborForce");
						int maleLaborForce = results.getInt("MaleLaborForce");
						int occupiedHousingUnits = results.getInt("OccupiedHousingUnits");
						int ownerOccupiedUnits = results.getInt("OwnerOccupiedUnits");
						int renterOccupiedUnits = results.getInt("RenterOccupiedUnits");
						String resultNeighborhoodName = results.getString("NeighborhoodName");
						int educationalAttainmentId = results.getInt("EdAttainmentId");
						EducationalAttainment.EdType edType = EducationalAttainment.EdType.get(results.getString("EdType"));
						int edPopulation = results.getInt("EdPopulation");
						
						
						Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
						Demographic demographic = new Demographic(demographicId,population, foriegnBorn,
								femaleLaborForce, maleLaborForce, occupiedHousingUnits, ownerOccupiedUnits,renterOccupiedUnits, 
								 neighborhood, educationalAttainmentId, edType, edPopulation);
						demographics.add(demographic);
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
			return demographics;
		}


}
