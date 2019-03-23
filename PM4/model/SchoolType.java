package model;

import java.util.HashMap;
import java.util.Map;

public class SchoolType {
	
	protected int schoolTypeId;
	protected PublicSchool school;
	protected String gradesOffered;
	protected SchoolTypology schoolTypology;
	
	public enum SchoolTypology {
	    ELEMENTARY_SCHOOL("Elementary School"), 
	    HIGH_SCHOOL("High School"),
	    SPECIAL("Special"),
	    K_8("k-8"),
	    MIDDLE_SCHOOL("Middle School"),
	    EARLY_LEARNING("Early Learning");
		
		private String type;
		
		// Reverse-lookup map for getting a day from an abbreviation
	    private static final Map<String, SchoolTypology> lookup = new HashMap<String, SchoolTypology>();

	    static {
	        for (SchoolTypology d : SchoolTypology.values()) {
	            lookup.put(d.type(), d);
	        }
	    }


	    SchoolTypology(String type) {
	        this.type = type;
	    }

	    public String type() {
	        return type;
	    }
	    
	    public static SchoolTypology get(String type) {
	        return lookup.get(type);
	    }
	}
	
	public SchoolType(int schoolTypeId,PublicSchool school,String gradesOffered, SchoolTypology schoolTypology) {
		this.schoolTypeId = schoolTypeId;
		this.school = school;
		this.gradesOffered = gradesOffered;
		this.schoolTypology = schoolTypology;
	}
	
	public SchoolType(int schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}
	
	public SchoolType(PublicSchool school,String gradesOffered, SchoolTypology schoolTypology) {
		this.school = school;
		this.gradesOffered = gradesOffered;
		this.schoolTypology = schoolTypology;
	}
	
	public int getSchoolTypeId() {
		return schoolTypeId;
	}

	public void setSchoolTypeId(int schoolTypeId) {
		this.schoolTypeId = schoolTypeId;
	}

	public PublicSchool getSchool() {
		return school;
	}

	public void setSchool(PublicSchool school) {
		this.school = school;
	}

	public String getGradesOffered() {
		return gradesOffered;
	}

	public void setGradesOffered(String gradesOffered) {
		this.gradesOffered = gradesOffered;
	}

	public SchoolTypology getSchoolTypology() {
		return schoolTypology;
	}

	public void setSchoolTypology(SchoolTypology schoolTypology) {
		this.schoolTypology = schoolTypology;
	}


}
