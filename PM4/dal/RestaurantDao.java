package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class RestaurantDao {

	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RestaurantDao instance = null;
	protected RestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantDao getInstance() {
		if(instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}

	// Create Restaurant
	public Restaurant create(Restaurant restaurant) throws SQLException {
		String insertRestaurant =
			"INSERT INTO Restaurant (RestaurantId, RestaurantName, LicenseStatus, RestaurantType, Address, City, State, Zip)" + 
			"VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);	
			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setString(2, restaurant.getRestaurantName());
			insertStmt.setString(3, restaurant.getLicenseStatus());
			insertStmt.setString(4, restaurant.getRestaurantType().value());
			insertStmt.setString(5, restaurant.getAddress());
			insertStmt.setString(6, restaurant.getCity());
			insertStmt.setString(7, restaurant.getState());
			insertStmt.setInt(8, restaurant.getZipCode().getZip());
			
			insertStmt.executeUpdate();
	
			return restaurant;
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

	//Delete Restaurant
	public Restaurant delete(Restaurant restaurant) throws SQLException {
		String deleteRestaurant = "DELETE FROM Restaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
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

	//getRestaurantByRestaurantId
	public Restaurant getRestaurantByRestaurantId(int restaurantId) throws SQLException {
		String selectRestaurant =
			"SELECT RestaurantId, RestaurantName, LicenseStatus, RestaurantType, Address, City, State, Zip " +
			"FROM Restaurant " +
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();
			ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
			if(results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String restaurantName = results.getString("RestaurantName");
				String licenseStatus = results.getString("LicenseStatus");
				Restaurant.RestaurantType restaurantType = Restaurant.RestaurantType.get(results.getString("RestaurantType"));
				String address = results.getString("Address");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				
				ZipCode zipCode = zipCodeDao.getZipCodeByZip(zip);
				Restaurant restaurant = new Restaurant(resultRestaurantId, restaurantName, licenseStatus, restaurantType, 
						address, city, state, zipCode);
				return restaurant;
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
	
	//getRestaurantByZip
	public List<Restaurant> getRestaurantByZipCode(int zip) throws SQLException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		String selectRestaurant =
				"SELECT RestaurantId, RestaurantName, LicenseStatus, RestaurantType, Address, City, State, Zip " +
				"FROM Restaurant " +
				"WHERE Zip=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRestaurant);
				selectStmt.setInt(1, zip);
				results = selectStmt.executeQuery();
				ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
				while(results.next()) {
					int resultRestaurantId = results.getInt("RestaurantId");
					String restaurantName = results.getString("RestaurantName");
					String licenseStatus = results.getString("LicenseStatus");
					Restaurant.RestaurantType restaurantType = Restaurant.RestaurantType.get(results.getString("RestaurantType"));
					String address = results.getString("Address");
					String city = results.getString("City");
					String state = results.getString("State");
					int resultZip = results.getInt("Zip");
					
					ZipCode zipCode = zipCodeDao.getZipCodeByZip(resultZip);
					Restaurant restaurant = new Restaurant(resultRestaurantId, restaurantName, licenseStatus, restaurantType, 
							address, city, state, zipCode);
					restaurants.add(restaurant);
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
		return restaurants;
	}

	//getRestaurantByRestaurantType
	public List<Restaurant> getRestaurantByRestaurantType(Restaurant.RestaurantType restaurantType) throws SQLException {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		String selectRestaurant =
				"SELECT RestaurantId, RestaurantName, LicenseStatus, RestaurantType, Address, City, State, Zip " +
				"FROM Restaurant " +
				"WHERE RestaurantType=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRestaurant);
				selectStmt.setString(1, restaurantType.value());
				results = selectStmt.executeQuery();
				ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
				while(results.next()) {
					int restaurantId = results.getInt("RestaurantId");
					String restaurantName = results.getString("RestaurantName");
					String licenseStatus = results.getString("LicenseStatus");
					Restaurant.RestaurantType resultRestaurantType = Restaurant.RestaurantType.get(results.getString("RestaurantType"));
					String address = results.getString("Address");
					String city = results.getString("City");
					String state = results.getString("State");
					int resultZip = results.getInt("Zip");
					
					ZipCode zipCode = zipCodeDao.getZipCodeByZip(resultZip);
					Restaurant restaurant = new Restaurant(restaurantId, restaurantName, licenseStatus, resultRestaurantType, 
							address, city, state, zipCode);
					restaurants.add(restaurant);
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
		return restaurants;
	}


	
}
