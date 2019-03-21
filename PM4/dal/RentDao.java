package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class RentDao {

	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RentDao instance = null;
	protected RentDao() {
		connectionManager = new ConnectionManager();
	}
	public static RentDao getInstance() {
		if(instance == null) {
			instance = new RentDao();
		}
		return instance;
	}

	// Create Rent
	public Rent create(Rent rent) throws SQLException {
		String insertRent =
			"INSERT INTO Rent (Neighborhoodname, OccupancyType, Price)" + 
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRent,
				Statement.RETURN_GENERATED_KEYS);		
			insertStmt.setString(1, rent.getNeighborhood().getNeighborhoodName());
			insertStmt.setString(2, rent.getOccupancyType().value());
			insertStmt.setDouble(3, rent.getPrice());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int rentId = -1;
			if(resultKey.next()) {
				rentId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rent.setRentId(rentId);
			return rent;
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

	//Delete Rent
	public Rent delete(Rent rent) throws SQLException {
		String deleteRent = "DELETE FROM Rent WHERE RentId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRent);
			deleteStmt.setInt(1, rent.getRentId());
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

	//getRentByRentId
	public Rent getRentByRentId(int rentId) throws SQLException {
		String selectRent =
			"SELECT RentId, NeighborhoodName, OccupancyType, Price " +
			"FROM Rent " +
			"WHERE RentId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRent);
			selectStmt.setInt(1, rentId);
			results = selectStmt.executeQuery();
			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
			if(results.next()) {
				int resultRentId = results.getInt("RentId");
				String neighborhoodName = results.getString("NeighborhoodName");
				Rent.OccupancyType occupancyType = Rent.OccupancyType.get(results.getString("OccupancyType"));
				double price = results.getDouble("Price");
				
				Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(neighborhoodName);
				Rent rent = new Rent(resultRentId,  neighborhood, occupancyType, price);
				return rent;
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
	
	//getRentByNeighborhoodName
	public List<Rent> getRentByNeighborhoodName(String neighborhoodName) throws SQLException {
		List<Rent> rents = new ArrayList<Rent>();
		String selectRent =
				"SELECT RentId, NeighborhoodName, OccupancyType, Price " +
				"FROM Rent " +
				"WHERE NeighborhoodName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRent);
				selectStmt.setString(1, neighborhoodName);
				results = selectStmt.executeQuery();
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				if(results.next()) {
					int resultRentId = results.getInt("RentId");
					String resultNeighborhoodName = results.getString("NeighborhoodName");
					Rent.OccupancyType occupancyType = Rent.OccupancyType.get(results.getString("OccupancyType"));
					double price = results.getDouble("Price");
					
					Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
					Rent rent = new Rent(resultRentId,  neighborhood, occupancyType, price);
					rents.add(rent);
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
		return rents;
	}

	//getRentByOccupancyType
	public List<Rent> getRentByOccupancyType(Rent.OccupancyType occupancyType) throws SQLException {
		List<Rent> rents = new ArrayList<Rent>();
		String selectRent =
				"SELECT RentId, NeighborhoodName, OccupancyType, Price " +
				"FROM Rent " +
				"WHERE OccupancyType=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRent);
				selectStmt.setString(1, occupancyType.value());
				results = selectStmt.executeQuery();
				NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
				if(results.next()) {
					int rentId = results.getInt("RentId");
					String resultNeighborhoodName = results.getString("NeighborhoodName");
					Rent.OccupancyType resultOccupancyType = Rent.OccupancyType.get(results.getString("OccupancyType"));
					double price = results.getDouble("Price");
					
					Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByNeighborhoodName(resultNeighborhoodName);
					Rent rent = new Rent(rentId,  neighborhood, resultOccupancyType, price);
					rents.add(rent);
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
		return rents;
	}

	
}
