package model;


import java.util.HashMap;
import java.util.Map;


public class EducationalAttainment {

	protected int edAttainmentId;
	protected Demographic demographicId;
	protected EdType edType;
	protected int edPopulation;
	
	public enum EdType {
	    LESS_THAN_HIGHSCHOOL("less than High school"), 
	    HIGHSCHOOL_OR_GED("High School or GED"),
	    SOME_COLLEGE_OR_ASSOCIATES_DEGREE_("some college or Associate's Degree"),
	    BACHELORS_DEGREE_OR_HIGHER("Bachelor's Degree or Higher");
	   
	   	
		private String value;
		
	    private static final Map<String, EdType> lookup = new HashMap<String, EdType>();

	    static {
	        for (EdType d : EdType.values()) {
	            lookup.put(d.value(), d);
	        }
	    }

	    EdType(String value) {
	        this.value = value;
	    }

	    public String value() {
	        return value;
	    }
	    
	    public static EdType get(String value) {
	        return lookup.get(value);
	    }
	}
	
	public EducationalAttainment(int edAttainmentId, Demographic demographicId, EdType edType, int edPopulation) {
		this.edAttainmentId = edAttainmentId;
		this.demographicId = demographicId;
		this.edType = edType;
		this.edPopulation = edPopulation;
	}
	
	public EducationalAttainment(Demographic demographicId, EdType edType, int edPopulation) {
		this.demographicId = demographicId;
		this.edType = edType;
		this.edPopulation = edPopulation;
	}

	public EducationalAttainment(int edAttainmentId) {
		this.edAttainmentId=edAttainmentId;
	}
	
	//getters and setters
	
	public int getEdAttainmentId() {
		return edAttainmentId;
	}

	public void setEdAttainmentId(int edAttainmentId) {
		this.edAttainmentId = edAttainmentId;
	}

	public Demographic getDemographicId() {
		return demographicId;
	}

	public void setDemographicId(Demographic demographicId) {
		this.demographicId = demographicId;
	}

	public EdType getEdType() {
		return edType;
	}

	public void setEdType(EdType edType) {
		this.edType = edType;
	}

	public int getEdPopulation() {
		return edPopulation;
	}

	public void setEdPopulation(int edPopulation) {
		this.edPopulation = edPopulation;
	}
	
}