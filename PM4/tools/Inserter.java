package tools;

import java.sql.SQLException;
import java.util.List;

import dal.*;
import model.*;

public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
		PublicSchoolDao publicSchoolDao = PublicSchoolDao.getInstance();
		
		// INSERT 
		
		//Create Neighborhood
		Neighborhood neighborhood = new Neighborhood("Fenway");
		neighborhood = neighborhoodDao.create(neighborhood);
		System.out.println("Created Neighborhood");
		
		//Create ZipCode
		ZipCode zipCode = new ZipCode(2215,neighborhood);
		zipCode = zipCodeDao.create(zipCode);
		ZipCode zipCode2 = new ZipCode(2216,neighborhood);
		zipCode2 = zipCodeDao.create(zipCode2);
		System.out.println("Created ZipCode");
		
		//Create Public School
		PublicSchool publicSchool = new PublicSchool( "SchoolName", "HistoricalName", "AbbreviatedName", "Address", "EducationProgram", 2018, 2019, 
				"SchoolOpen", "SchoolClose", "SchoolEnrollment",neighborhood);
		publicSchool = publicSchoolDao.create(publicSchool);
		System.out.println("Created PublicSchool");		
		
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//READ
		
		//getNeighborhoodByNeighborhoodName
		Neighborhood neighborhoodRead = neighborhoodDao.getNeighborhoodByNeighborhoodName("Fenway");
		System.out.format("Reading Neighborhood: NeighborhoodName is %s \n",
				neighborhoodRead.getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getZipCodeByZip
		ZipCode zipCodeRead = zipCodeDao.getZipCodeByZip(2215);
		System.out.format("Reading Neighborhood: Zip is %s, NeighborhoodName is %s \n",
				zipCodeRead.getZip(), zipCodeRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getZipCodeByNeighborhoodName
		List<ZipCode> zipCodeRead2 = zipCodeDao.getZipCodeByNeighborhoodName("Fenway");
		for(ZipCode zc : zipCodeRead2) {
			System.out.format("Reading Neighborhood: Zip is %s, NeighborhoodName is %s \n",
					zc.getZip(), zc.getNeighborhood().getNeighborhoodName());
		}
		
		System.out.println("-----------------------------------------------------------------------------\n");	
		
		//getZipCodeByZip
		PublicSchool publicSchoolRead = publicSchoolDao.getPublicSchoolBySchoolId(1);
		System.out.format("Reading PublicSchool: SchoolName is %s, NeighborhoodName is %s \n",
				publicSchoolRead.getSchoolName(), publicSchoolRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getZipCodeByNeighborhoodName
		List<PublicSchool> publicSchoolRead2 = publicSchoolDao.getPublicSchoolByNeighborhoodName("Fenway");
		for(PublicSchool ps : publicSchoolRead2) {
			System.out.format("Reading PublicSchool: SchoolName is %s, NeighborhoodName is %s \n",
					ps.getSchoolName(), ps.getNeighborhood().getNeighborhoodName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		// DELETE
		
		//Remove CreditCard
		neighborhoodDao.delete(neighborhood);
		System.out.println("Deleting Neighborhood");
		
		zipCodeDao.delete(zipCode);
		zipCodeDao.delete(zipCode2);
		System.out.println("Deleting ZipCode");
		
		publicSchoolDao.delete(publicSchoolRead);
		System.out.println("Deleting PublicSchool");	
	}
}
