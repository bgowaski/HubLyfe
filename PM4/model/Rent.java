package model;

import java.util.HashMap;
import java.util.Map;


public class Rent {


	protected int rentId;
	protected Neighborhood neighborhood;
	protected OccupancyType occupancyType;
	protected double price; //Should make price decimal(6,2)
	
	
	public enum OccupancyType {
	    ALL_RENTALS("All rentals"), 
	    STUDIO("Studio"),
	    BED1("1 Bed"),
	    BEDS2("2 Beds"),
	    BEDS3("3 Beds");
	   	
		private String value;
		
	    private static final Map<String, OccupancyType> lookup = new HashMap<String, OccupancyType>();

	    static {
	        for (OccupancyType d : OccupancyType.values()) {
	            lookup.put(d.value(), d);
	        }
	    }

	    OccupancyType(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	    
	    public static OccupancyType get(String value) {
	        return lookup.get(value);
	    }
	}
	
	public Rent(int rentId, Neighborhood neighborhood, OccupancyType occupancyType, double price) {
		this.rentId = rentId;
		this.neighborhood = neighborhood;
		this.occupancyType = occupancyType;
		this.price = price;
	}

	public Rent(int rentId) {
		this.rentId = rentId;
	}
	
	public Rent( Neighborhood neighborhood, OccupancyType occupancyType, double price) {
		this.neighborhood = neighborhood;
		this.occupancyType = occupancyType;
		this.price = price;
	}
	//Getters and Setters
	public int getRentId() {
		return rentId;
	}


	public void setRentId(int rentId) {
		this.rentId = rentId;
	}


	public Neighborhood getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public OccupancyType getOccupancyType() {
		return occupancyType;
	}

	public void setOccupancyType(OccupancyType occupancyType) {
		this.occupancyType = occupancyType;
	}
}
