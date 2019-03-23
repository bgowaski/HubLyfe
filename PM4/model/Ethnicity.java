package model;

import java.util.HashMap;
import java.util.Map;


public class Ethnicity {

	protected int ethnicityId;
	protected Demographic demographic;
	protected EthnicityType ethnicityType; 
	protected int ethnicityPopulation;
	
	public enum EthnicityType {
	    WHITE("White"), 
	    AFRICAN_AMARICAN("Black/ African American"),
	    HISPANIC("Hispanic"),
	    ASIAN("Asian/PI"),
	    OTHER("Other");
		
		private String value;
		
		// Reverse-lookup map for getting a day from an abbreviation
	    private static final Map<String, EthnicityType> lookup = new HashMap<String, EthnicityType>();

	    static {
	        for (EthnicityType d : EthnicityType.values()) {
	            lookup.put(d.value(), d);
	        }
	    }


	    EthnicityType(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	    
	    public static EthnicityType get(String value) {
	        return lookup.get(value);
	    }
	}
	
	public Ethnicity(int ethnicityId,Demographic demographic,EthnicityType ethnicityType, int ethnicityPopulation) {
		this.ethnicityId = ethnicityId;
		this.demographic = demographic;
		this.ethnicityType = ethnicityType;
		this.ethnicityPopulation = ethnicityPopulation;
	}
	
	public Ethnicity(int ethnicityId) {
		this.ethnicityId = ethnicityId;
	}
	
	public Ethnicity(Demographic demographic,EthnicityType ethnicityType, int ethnicityPopulation) {
		this.demographic = demographic;
		this.ethnicityType = ethnicityType;
		this.ethnicityPopulation = ethnicityPopulation;
	}
	
	public int getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(int ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public Demographic getDemographic() {
		return demographic;
	}

	public void setDemographic(Demographic demographic) {
		this.demographic = demographic;
	}

	public EthnicityType getEthnicityType() {
		return ethnicityType;
	}

	public void setEthnicityType(EthnicityType ethnicityType) {
		this.ethnicityType = ethnicityType;
	}

	public int getEthnicityPopulation() {
		return ethnicityPopulation;
	}

	public void setEthnicityPopulation(int ethnicityPopulation) {
		this.ethnicityPopulation = ethnicityPopulation;
	}


}
