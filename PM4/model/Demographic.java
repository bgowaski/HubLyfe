package model;

import model.AgeData.AgeRange;
import model.EducationalAttainment.EdType;
import model.Ethnicity.EthnicityType;

public class Demographic {

	protected int demographicId;
	protected double population; 
	protected int foriegnBorn;
	protected int femaleLaborForce;
	protected int maleLaborForce;
	protected int occupiedHousingUnits;
	protected int ownerOccupiedUnits;
	protected int renterOccupiedUnits;
	protected Neighborhood neighborhood;

	public int getEdAttainmentId() {
		return edAttainmentId;
	}

	public void setEdAttainmentId(int edAttainmentId) {
		this.edAttainmentId = edAttainmentId;
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

	protected int edAttainmentId;
	protected EdType edType;
	protected int edPopulation;
	
	public Demographic(int demographicId,double population,int foriegnBorn,int femaleLaborForce,
			int maleLaborForce,int occupiedHousingUnits, int ownerOccupiedUnits, int renterOccupiedUnits,
		    Neighborhood neighborhood, 
		    int edAttainmentId, EdType edType, int edPopulation) {

		this.demographicId = demographicId;
		this.population = population;
		this.foriegnBorn = foriegnBorn;
		this.femaleLaborForce = femaleLaborForce;
		this.maleLaborForce = maleLaborForce;
		this.occupiedHousingUnits = occupiedHousingUnits;
		this.ownerOccupiedUnits = ownerOccupiedUnits;
		this.renterOccupiedUnits = renterOccupiedUnits;
		this.neighborhood = neighborhood;
		this.edAttainmentId = edAttainmentId;
		this.edType = edType;
		this.edPopulation = edPopulation;
	}
	
	public Demographic(int demographicId,double population,int foriegnBorn,int femaleLaborForce,
			int maleLaborForce,int occupiedHousingUnits, int ownerOccupiedUnits, int renterOccupiedUnits,
		    Neighborhood neighborhood) {

		this.demographicId = demographicId;
		this.population = population;
		this.foriegnBorn = foriegnBorn;
		this.femaleLaborForce = femaleLaborForce;
		this.maleLaborForce = maleLaborForce;
		this.occupiedHousingUnits = occupiedHousingUnits;
		this.ownerOccupiedUnits = ownerOccupiedUnits;
		this.renterOccupiedUnits = renterOccupiedUnits;
		this.neighborhood = neighborhood;
	}

	public Demographic(int demographicId) {

		this.demographicId = demographicId;
	}

	public Demographic(double population,int foriegnBorn,int femaleLaborForce,
			int maleLaborForce,int occupiedHousingUnits, int ownerOccupiedUnits, int renterOccupiedUnits,
		    Neighborhood neighborhood) {

	
		this.population = population;
		this.foriegnBorn = foriegnBorn;
		this.femaleLaborForce = femaleLaborForce;
		this.maleLaborForce = maleLaborForce;
		this.occupiedHousingUnits = occupiedHousingUnits;
		this.ownerOccupiedUnits = ownerOccupiedUnits;
		this.renterOccupiedUnits = renterOccupiedUnits;
		this.neighborhood = neighborhood;
	}

	
	public int getDemographicId() {
		return demographicId;
	}
	public void setDemographicId(int demographicId) {
		this.demographicId = demographicId;
	}
	public double getPopulation() {
		return population;
	}
	public void setPopulation(double population) {
		this.population = population;
	}
	public int getForiegnBorn() {
		return foriegnBorn;
	}
	public void setForiegnBorn(int foriegnBorn) {
		this.foriegnBorn = foriegnBorn;
	}
	public int getFemaleLaborForce() {
		return femaleLaborForce;
	}
	public void setFemaleLaborForce(int femaleLaborForce) {
		this.femaleLaborForce = femaleLaborForce;
	}
	public int getMaleLaborForce() {
		return maleLaborForce;
	}
	public void setMaleLaborForce(int maleLaborForce) {
		this.maleLaborForce = maleLaborForce;
	}
	public int getOccupiedHousingUnits() {
		return occupiedHousingUnits;
	}
	public void setOccupiedHousingUnits(int occupiedHousingUnits) {
		this.occupiedHousingUnits = occupiedHousingUnits;
	}
	public int getOwnerOccupiedUnits() {
		return ownerOccupiedUnits;
	}
	public void setOwnerOccupiedUnits(int ownerOccupiedUnits) {
		this.ownerOccupiedUnits = ownerOccupiedUnits;
	}
	public int getRenterOccupiedUnits() {
		return renterOccupiedUnits;
	}
	public void setRenterOccupiedUnits(int renterOccupiedUnits) {
		this.renterOccupiedUnits = renterOccupiedUnits;
	}
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
}
