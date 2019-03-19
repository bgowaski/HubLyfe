package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ZipCodeDao {

	protected ConnectionManager connectionManager;

	private static ZipCodeDao instance = null;
	protected ZipCodeDao() {
		connectionManager = new ConnectionManager();
	}
	public static ZipCodeDao getInstance() {
		if(instance == null) {
			instance = new ZipCodeDao();
		}
		return instance;
	}

	//Create ZipCode
	public ZipCode create(ZipCode zipCode) throws SQLException {
		String insertZipCode =
			"INSERT INTO ZipCode(Zip, NeighborhoodName) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertZipCode);
			insertStmt.setInt(1, zipCode.getZip());
			insertStmt.setString(2, zipCode.getNeighborhood().getNeighborhoodName());
			insertStmt.executeUpdate();
			
			return zipCode;
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

	//Update Neighborhood
	public ZipCode updateNeighborhood(ZipCode zipCode, String newNeighborhood) throws SQLException {
		String updateNeighborhood = "UPDATE ZipCode SET NeighborhoodName=? WHERE Zip=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNeighborhood);
			updateStmt.setString(1, newNeighborhood);
			updateStmt.setInt(2, zipCode.getZip());
			
			updateStmt.executeUpdate();
			return zipCode;
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


	//Delete ZipCode 
	public ZipCode delete(ZipCode zipCode) throws SQLException {
		String deleteZipCode = "DELETE FROM ZipCode WHERE Zip=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteZipCode);
			deleteStmt.setInt(1, zipCode.getZip());
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

	//getZipCodeByZip
	public ZipCode getZipCodeByZip(int zip) throws SQLException {
		String selectZipCode =
			"SELECT Zip, NeighborhoodName " +
			"FROM ZipCode " +
			"WHERE Zip=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectZipCode);
			selectStmt.setInt(1, zip);
			results = selectStmt.executeQuery();
			
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			if(results.next()) {
				int resultZip = results.getInt("Zip");
				String neighborhoodName = results.getString("NeighborhoodName");
				
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(neighborhoodName);
				ZipCode zipCode = new ZipCode(resultZip, neighborhood);
				return zipCode;
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


	//getZipCodeByNeighborhoodName
	public List<ZipCode> getZipCodeByNeighborhoodName(String neighborhoodName) throws SQLException {
		List<ZipCode> zipCodes = new ArrayList<ZipCode>();
		String selectZipCodes =
				"SELECT Zip, NeighborhoodName " +
				"FROM ZipCode " +
				"WHERE NeighborhoodName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectZipCodes);
			selectStmt.setString(1,neighborhoodName );
			results = selectStmt.executeQuery();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			while(results.next()) {
				int zip = results.getInt("Zip");
				String resultNeighborhoodName = results.getString("NeighborhoodName");

				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
				ZipCode zipCode = new ZipCode(zip,neighborhood);
				zipCodes.add(zipCode);
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
		return zipCodes;
	}
}
