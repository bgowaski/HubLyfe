package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class PublicSchoolDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static PublicSchoolDao instance = null;
	protected PublicSchoolDao() {
		connectionManager = new ConnectionManager();
	}
	public static PublicSchoolDao getInstance() {
		if(instance == null) {
			instance = new PublicSchoolDao();
		}
		return instance;
	}

	// Create PublicSchool
	public PublicSchool create(PublicSchool publicSchool) throws SQLException {
		String insertPublicSchool =
			"INSERT INTO PublicSchool (SchoolName, HistoricalName, AbbreviatedName, " + 
					" Address, EducationProgram,  YearFounded,  YearBuilt, SchoolOpen," + 
					" SchoolClose, SchoolEnrollment, NeighborhoodName)" + 
			"VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPublicSchool,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, publicSchool.getSchoolName());
			insertStmt.setString(2, publicSchool.getHistoricalName());
			insertStmt.setString(3, publicSchool.getAbbreviatedName());
			insertStmt.setString(4, publicSchool.getAddress());
			insertStmt.setString(5,publicSchool.getEducationProgram());
			insertStmt.setInt(6, publicSchool.getYearFounded());
			insertStmt.setInt(7, publicSchool.getYearBuilt());
			insertStmt.setString(8, publicSchool.getSchoolOpen());
			insertStmt.setString(9, publicSchool.getSchoolClose());
			insertStmt.setString(10, publicSchool.getSchoolEnrollment());
			insertStmt.setString(11, publicSchool.getNeighborhood().getNeighborhoodName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int schoolId = -1;
			if(resultKey.next()) {
				schoolId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			publicSchool.setSchoolId(schoolId);
			return publicSchool;
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

	//Delete PublicSchool
	public PublicSchool delete(PublicSchool publicSchool) throws SQLException {
		String deletePublicSchool = "DELETE FROM PublicSchool WHERE SchoolId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePublicSchool);
			deleteStmt.setInt(1, publicSchool.getSchoolId());
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

	//getPublicSchoolBySchoolId
	public PublicSchool getPublicSchoolBySchoolId(int schoolId) throws SQLException {
		String selectPublicSchool =
			"SELECT SchoolId, SchoolName, HistoricalName, AbbreviatedName, Address, EducationProgram,  YearFounded,  "
			+ "YearBuilt, SchoolOpen, SchoolClose, SchoolEnrollment, NeighborhoodName " +
			"FROM PublicSchool " +
			"WHERE SchoolId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPublicSchool);
			selectStmt.setInt(1, schoolId);
			results = selectStmt.executeQuery();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			if(results.next()) {
				int resultSchoolId = results.getInt("SchoolId");
				String schoolName = results.getString("SchoolName");
				String historicalName = results.getString("HistoricalName");
				String abbreviatedName = results.getString("AbbreviatedName");
				String address = results.getString("Address");
				String educationProgram = results.getString("EducationProgram");
				int YearFounded = results.getInt("YearFounded");
				int YearBuilt = results.getInt("YearBuilt");
				String SchoolOpen = results.getString("SchoolOpen");
				String SchoolClose = results.getString("SchoolClose");
				String SchoolEnrollment = results.getString("SchoolEnrollment");
				String neighborhoodName = results.getString("NeighborhoodName");
				
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(neighborhoodName);
				PublicSchool publicSchool = new PublicSchool(resultSchoolId,schoolName, historicalName,
						abbreviatedName, address, educationProgram, YearFounded,YearBuilt, SchoolOpen, SchoolClose,
						SchoolEnrollment, neighborhood);
				return publicSchool;
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
	
	//getPublicSchoolByNeighborhoodName
	public List<PublicSchool> getPublicSchoolByNeighborhoodName(String neighborhoodName) throws SQLException {
		List<PublicSchool> publicSchools = new ArrayList<PublicSchool>();
		String selectPublicSchool =
				"SELECT SchoolId, SchoolName, HistoricalName, AbbreviatedName, Address, EducationProgram,  YearFounded,  "
				+ "YearBuilt, SchoolOpen, SchoolClose, SchoolEnrollment, NeighborhoodName " +
				"FROM PublicSchool " +
				"WHERE NeighborhoodName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectPublicSchool);
				selectStmt.setString(1, neighborhoodName);
				results = selectStmt.executeQuery();
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				while(results.next()) {
					int schoolId = results.getInt("SchoolId");
					String schoolName = results.getString("SchoolName");
					String historicalName = results.getString("HistoricalName");
					String abbreviatedName = results.getString("AbbreviatedName");
					String address = results.getString("Address");
					String educationProgram = results.getString("EducationProgram");
					int YearFounded = results.getInt("YearFounded");
					int YearBuilt = results.getInt("YearBuilt");
					String SchoolOpen = results.getString("SchoolOpen");
					String SchoolClose = results.getString("SchoolClose");
					String SchoolEnrollment = results.getString("SchoolEnrollment");
					String resultNeighborhoodName = results.getString("NeighborhoodName");
					
					Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
					PublicSchool publicSchool = new PublicSchool(schoolId,schoolName, historicalName,
							abbreviatedName, address, educationProgram, YearFounded,YearBuilt, SchoolOpen, SchoolClose,
							SchoolEnrollment, neighborhood);
					publicSchools.add(publicSchool);
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
		return publicSchools;
	}


}
