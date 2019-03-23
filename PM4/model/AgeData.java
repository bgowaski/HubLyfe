package model;

import java.util.HashMap;
import java.util.Map;


public class AgeData {

	protected int ageId;
	protected int demographicId;
	protected AgeRange ageRange;
	protected int agePercentage;
	
	public enum AgeRange {
	    ZERO_TO_NINE_YEARS("0-9 years"), 
	    TEN_TO_NINETEEN_YEARS("10-19 years"),
	    TWENTY_TO_THIRTYFOUR_YEARS("20-34 years"),
	    THIRTY_TO_FIFTYFOUR_YEARS("35-54 years"),
	    FIFTYFIVE_SIXTYFOUR_YEARS("55-64 years"),
	    SIXTYFIVE_YEARS_ANG_OVER("65 years and over");
	   	
		private String value;
		
	    private static final Map<String, AgeRange> lookup = new HashMap<String, AgeRange>();

	    static {
	        for (AgeRange d : AgeRange.values()) {
	            lookup.put(d.value(), d);
	        }
	    }

	    AgeRange(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	    
	    public static AgeRange get(String value) {
	        return lookup.get(value);
	    }
	}
	
	public AgeData(int ageId, int demographicId, AgeRange ageRange, int agePercentage) {
		this.ageId = ageId;
		this.demographicId = demographicId;
		this.ageRange = ageRange;
		this.agePercentage = agePercentage;
	}

	public AgeData(int ageId) {
		this.ageId=ageId;
	}
	
	//Getters and Setters
	
	public int getAgeId() {
		return ageId;
	}

	public void setAgeId(int ageId) {
		this.ageId = ageId;
	}

	public int getDemographicId() {
		return demographicId;
	}

	public void setDemographicId(int demographicId) {
		this.demographicId = demographicId;
	}

	public AgeRange getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(AgeRange ageRange) {
		this.ageRange = ageRange;
	}

	public int getAgePercentage() {
		return agePercentage;
	}

	public void setAgePercentage(int agePercentage) {
		this.agePercentage = agePercentage;
	}
	
}