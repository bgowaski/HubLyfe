package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Neighborhood;


public class NeighborhoodDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static NeighborhoodDao instance = null;
	protected NeighborhoodDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodDao();
		}
		return instance;
	}

	//Create Neighborhood
	public Neighborhood create(Neighborhood neighborhood) throws SQLException {
		String insertNeighborhood = "INSERT INTO Neighborhood(NeighborhoodName) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood);

			insertStmt.setString(1, neighborhood.getNeighborhoodName());
			

			insertStmt.executeUpdate();
		
			return neighborhood;
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

	
	//Delete Neighborhood
	public Neighborhood delete(Neighborhood neighborhood) throws SQLException {
		String deleteNeighborhood = "DELETE FROM Neighborhood WHERE NeighborhoodName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setString(1, neighborhood.getNeighborhoodName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
	

	//getNeighborhoodByNeighborhoodName
	public Neighborhood getNeighborhoodByNeighborhoodName(String neighborhoodName) throws SQLException {
		
		String selectNeighborhood =
				"SELECT NeighborhoodName " +
				"FROM Neighborhood " +
				"WHERE NeighborhoodName=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectNeighborhood);
				selectStmt.setString(1, neighborhoodName);
				results = selectStmt.executeQuery();
				
				if(results.next()) {
					String resultNeighborhoodname = results.getString("NeighborhoodName");
					
					Neighborhood neighborhood = new Neighborhood(resultNeighborhoodname);
					return neighborhood;
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
