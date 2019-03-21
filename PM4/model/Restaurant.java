package model;

import java.util.HashMap;
import java.util.Map;


public class Restaurant {
	
	protected int restaurantId;
	protected String restaurantName;
	protected String licenseStatus;
	protected RestaurantType restaurantType;
	protected String address;
	protected String city;
	protected String state;
	protected ZipCode zipCode;
	
	public enum RestaurantType {
	    EATING_AND_DRINKING("Eating & Drinking"), 
	    RETAIL_FOOD("Retail Food"),
	    EATING_AND_DRINKING_WITH_TAKEOUT("Eating & Drinking w/ Take Out"),
	    MOBILE_FOOD_WALK_ON("Mobile Food Walk On");
	   	
		private String value;
		
	    private static final Map<String, RestaurantType> lookup = new HashMap<String, RestaurantType>();

	    static {
	        for (RestaurantType d : RestaurantType.values()) {
	            lookup.put(d.value(), d);
	        }
	    }

	    RestaurantType(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	    
	    public static RestaurantType get(String value) {
	        return lookup.get(value);
	    }
	}
	
	public Restaurant(int restaurantId, String restaurantName, String licenseStatus, RestaurantType restaurantType, 
			String address, String city, String state, ZipCode zipCode) {
		this.restaurantId=restaurantId;
		this.restaurantName = restaurantName;
		this.licenseStatus = licenseStatus;
		this.restaurantType = restaurantType;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public Restaurant(int restaurantId) {
		this.restaurantId=restaurantId;
	}

	
	//Getters and Setters
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getLicenseStatus() {
		return licenseStatus;
	}

	public void setLicenseStatus(String licenseStatus) {
		this.licenseStatus = licenseStatus;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ZipCode getZipCode() {
		return zipCode;
	}

	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}


}
