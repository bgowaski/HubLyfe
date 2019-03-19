package model;


public class ZipCode {

	protected int zip;
	protected Neighborhood neighborhood;

	
	public ZipCode(int zip, Neighborhood neighborhood) {
		this.zip = zip;
		this.neighborhood = neighborhood;
	}
	
	public ZipCode(int zip) {
		this.zip = zip;
	}
	
	public ZipCode( Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	//Getters and Setters
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}



}
