package tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dal.*;
import model.*;
import model.EducationalAttainment.EdType;
import model.AgeData.AgeRange;
import model.Ethnicity.EthnicityType;
import model.Rent.OccupancyType;
import model.Restaurant.RestaurantType;
import model.SchoolType.SchoolTypology;

public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		ZipCodeDao zipCodeDao = ZipCodeDao.getInstance();
		PublicSchoolDao publicSchoolDao = PublicSchoolDao.getInstance();
		SchoolTypeDao schoolTypeDao = SchoolTypeDao.getInstance();
		RentDao rentDao = RentDao.getInstance();
		RestaurantDao restaurantDao = RestaurantDao.getInstance();
		DemographicDao demographicDao = DemographicDao.getInstance();
		AgeDataDao ageDataDao = AgeDataDao.getInstance();
		EthnicityDao ethnicityDao = EthnicityDao.getInstance();
		UserDao userDao = UserDao.getInstance();
		JobDepartmentDao jobDepartmentDao = JobDepartmentDao.getInstance();
		JobDetailDao jobDetailDao = JobDetailDao.getInstance();
		EducationalAttainmentDao edAttainmentDao = EducationalAttainmentDao.getInstance();
		
		// INSERT 
/*		
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
		
		
		//Create School Type
		SchoolType schoolType = new SchoolType(publicSchool,"Grades", SchoolTypology.HIGH_SCHOOL);
		schoolType = schoolTypeDao.create(schoolType);
		System.out.println("Created SchoolType");	
		
		//Create Rent
		Rent rent = new Rent(neighborhood, OccupancyType.ALL_RENTALS, 34.2);
		rent = rentDao.create(rent);
		System.out.println("Created Rent");
		
		//Create Restaurant
		Restaurant restaurant = new Restaurant(1,"Name Res","Active", RestaurantType.MOBILE_FOOD_WALK_ON, "addressss", "cityyy", "stateee", zipCode2);
		restaurant = restaurantDao.create(restaurant);
		System.out.println("Created Restaurant");
		
		
		//Create Demographic
		 /*
		  *  
		 
		Demographic demographic = new Demographic(11111, 12, 13, 4, 15, 16,17, neighborhood);
		demographic = demographicDao.create(demographic);
		System.out.println("Created Demographic");
		
		//Create Ethnicity
		Ethnicity ethnicity = new Ethnicity(demographic, EthnicityType.HISPANIC, 123);
		ethnicity = ethnicityDao.create(ethnicity);
		System.out.println("Created Ethnicity");
		
		//Create User
		Date date = new Date();
		User user = new User("username", "firstname", "lastname", "password", date, 2215, 2216, "Teacher");
		user = userDao.create(user);
		System.out.println("Created User");
		
		//Create AgeData
		 
		 AgeData ageData = new AgeData(demographic, AgeRange.TEN_TO_NINETEEN_YEARS, 30);
		 ageData = ageDataDao.create(ageData);
		 System.out.println("Created AgeData");
			System.out.println("-----------------------------------------------------------------------------\n");
		 

		//Create JobDepartment
		JobDepartment jobDepartment = new JobDepartment("departmentName");
		jobDepartment = jobDepartmentDao.create(jobDepartment);
		System.out.println("Created JobDepartment");
		//Create JobDetail
		JobDetail jobDetail = new JobDetail(1,"Teacher",jobDepartment,50000,zipCode2);
		jobDetail = jobDetailDao.create(jobDetail);
		System.out.println("Created JobDetail");

		// Create EducationalAttainment
		EducationalAttainment edAttainment = new EducationalAttainment(demographic,EdType.LESS_THAN_HIGHSCHOOL,2000);
		edAttainment = edAttainmentDao.create(edAttainment);
		System.out.println("Created EducationalAttainment");
		System.out.println("-----------------------------------------------------------------------------\n");

		//READ
		
/*		//getNeighborhoodByNeighborhoodName
		Neighborhood neighborhoodRead = neighborhoodDao.getNeighborhoodByNeighborhoodName("Fenway");
		System.out.format("Reading Neighborhood: NeighborhoodName is %s \n",
				neighborhoodRead.getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getZipCodeByZip
		ZipCode zipCodeRead = zipCodeDao.getZipCodeByZip(2215);
		System.out.format("Reading ZipCode: Zip is %s, NeighborhoodName is %s \n",
				zipCodeRead.getZip(), zipCodeRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getZipCodeByNeighborhoodName
		List<ZipCode> zipCodeRead2 = zipCodeDao.getZipCodeByNeighborhoodName("Fenway");
		for(ZipCode zc : zipCodeRead2) {
			System.out.format("Reading ZipCode: Zip is %s, NeighborhoodName is %s \n",
					zc.getZip(), zc.getNeighborhood().getNeighborhoodName());
		}
		
		System.out.println("-----------------------------------------------------------------------------\n");	
		
		//getPublicSchoolBySchoolId
		PublicSchool publicSchoolRead = publicSchoolDao.getPublicSchoolBySchoolId(publicSchool.getSchoolId());
		System.out.format("Reading PublicSchool: SchoolName is %s, NeighborhoodName is %s \n",
				publicSchoolRead.getSchoolName(), publicSchoolRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getPublicSchoolByNeighborhoodName
		List<PublicSchool> publicSchoolRead2 = publicSchoolDao.getPublicSchoolByNeighborhoodName("Fenway");
		for(PublicSchool ps : publicSchoolRead2) {
			System.out.format("Reading PublicSchool: SchoolName is %s, NeighborhoodName is %s \n",
					ps.getSchoolName(), ps.getNeighborhood().getNeighborhoodName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		

		//getSchoolTypeBySchoolTypeId
		SchoolType schoolTypeRead = schoolTypeDao.getSchoolTypeBySchoolTypeId(schoolType.getSchoolTypeId());
		System.out.format("Reading SchoolType: SchoolId is %s, Typology is %s \n",
				schoolTypeRead.getSchoolTypeId(), schoolTypeRead.getSchoolTypology().type());
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getSchoolTypeBySchoolId
		List<SchoolType> schoolTypeRead2 = schoolTypeDao.getSchoolTypeBySchoolId(publicSchool.getSchoolId());
		for(SchoolType st : schoolTypeRead2) {
			System.out.format("Reading SchoolType: SchoolId is %s, Typology is %s \n",
					st.getSchoolTypeId(), st.getSchoolTypology().type());
		}
		System.out.println("-----------------------------------------------------------------------------\n");		
		
		
		//getSchoolTypeBySchoolTypology
		List<SchoolType> schoolTypeRead3 = schoolTypeDao.getSchoolTypeBySchoolTypology(SchoolTypology.HIGH_SCHOOL);
		for(SchoolType st2 : schoolTypeRead3) {
			System.out.format("Reading SchoolType: SchoolId is %s, Typology is %s \n",
					st2.getSchoolTypeId(), st2.getSchoolTypology().type());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getRentByRentId
		Rent rentRead = rentDao.getRentByRentId(rent.getRentId());
		System.out.format("Reading Rent: OccupancyType is %s, NeighborhoodName is %s \n",
				rentRead.getOccupancyType().value(), rentRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getRentByNeighborhoodName
		List<Rent> rentRead2 = rentDao.getRentByNeighborhoodName("Fenway");
		for(Rent rr : rentRead2) {
			System.out.format("Reading Rent: OccupancyType is %s, NeighborhoodName is %s \n",
					rr.getOccupancyType().value(), rr.getNeighborhood().getNeighborhoodName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getRentByOccupancyType
		List<Rent> rentRead3 = rentDao.getRentByOccupancyType(OccupancyType.ALL_RENTALS);
		for(Rent rr3 : rentRead3) {
			System.out.format("Reading Rent: OccupancyType is %s, NeighborhoodName is %s \n",
					rr3.getOccupancyType().value(), rr3.getNeighborhood().getNeighborhoodName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getRestaurantByRestaurantId
		Restaurant restaurantRead = restaurantDao.getRestaurantByRestaurantId(restaurant.getRestaurantId());
		System.out.format("Reading Restaurant: RestaurantType is %s, Zip is %s \n",
				restaurantRead.getRestaurantType().value(), restaurantRead.getZipCode().getZip());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getRestaurantByZipCode
		List<Restaurant> restaurantRead2 = restaurantDao.getRestaurantByZipCode(zipCode2.getZip());
		for(Restaurant res2 : restaurantRead2) {
			System.out.format("Reading Restaurant: RestaurantType is %s, Zip is %s \n",
					res2.getRestaurantType().value(), res2.getZipCode().getZip());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getRestaurantByRestaurantType
		List<Restaurant> restaurantRead3 = restaurantDao.getRestaurantByRestaurantType(RestaurantType.MOBILE_FOOD_WALK_ON);
		for(Restaurant res3 : restaurantRead3) {
			System.out.format("Reading Restaurant: RestaurantType is %s, Zip is %s \n",
					res3.getRestaurantType().value(), res3.getZipCode().getZip());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		
		//getDemographicByDemographicId
		Demographic demographicRead = demographicDao.getDemographicByDemographicId(1);
		System.out.format("Reading Demographic: Population is %s, NeighborhoodName is %s \n",
				demographicRead.getPopulation(), demographicRead.getNeighborhood().getNeighborhoodName());
		System.out.println("-----------------------------------------------------------------------------\n");
				
		//getDemographicByNeighborhoodName
		List<Demographic> demographicRead2 = demographicDao.getDemographicByNeighborhoodName("Fenway");
		for(Demographic d : demographicRead2) {
			System.out.format("Reading Demographic: Population is %s, NeighborhoodName is %s \n",
					d.getPopulation(), d.getNeighborhood().getNeighborhoodName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getEthnicityByEthnicityId
		Ethnicity ethnicityRead = ethnicityDao.getEthnicityByEthnicityId(ethnicity.getEthnicityId());
		System.out.format("Reading Ethnicity: Ethnicity is %s, Ethnicity Type is %s \n",
				ethnicityRead.getEthnicityId(), ethnicityRead.getEthnicityType().value());
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getEthnicityByDemographicId
		List<Ethnicity> ethnicityRead2 = ethnicityDao.getEthnicityByDemographicId(ethnicity.getDemographic().getDemographicId());
		for(Ethnicity et2 : ethnicityRead2) {
			System.out.format("Reading Ethnicity: Ethnicity is %s, Ethnicity Type is %s \n",
					et2.getEthnicityId(), et2.getEthnicityType().value());
		}
		System.out.println("-----------------------------------------------------------------------------\n");		
		
		
		//getEthnicityByEthnicityType
		List<Ethnicity> ethnicityRead3 = ethnicityDao.getEthnicityByEthnicityType(EthnicityType.HISPANIC);
		for(Ethnicity et3 : ethnicityRead3) {
			System.out.format("Reading Ethnicity: Ethnicity is %s, Ethnicity Type is %s \n",
					et3.getEthnicityId(), et3.getEthnicityType().value());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getUserByUserName
		User userRead = userDao.getUserByUserName("sheela27");
		System.out.format("Reading User: Username is %s, Firstname is %s \n",
				userRead.getUserName(), userRead.getFirstName());
		
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//
		List<User> userRead2 = userDao.getUserByFirstName("Sheela");
		for(User ur : userRead2) {
			System.out.format("Reading User: Username is %s, Firstname is %s \n",
					ur.getUserName(), ur.getFirstName());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
		//getJobDepartmentByDepartmentName
		JobDepartment jobDepartmentRead = jobDepartmentDao.getJobDepartmentByDepartmentName("departmentName");
		System.out.format("Reading JobDepartment: JobDepartment is %s",jobDepartmentRead.getDepartmentName());
		System.out.println("-----------------------------------------------------------------------------\n");

		//Get JobDetail
		JobDetail jobDetailRead = jobDetailDao.getJobDetailByJobTitleId(1);
		System.out.format("Reading JobDetail: JobTitleId is %d, JobTitle is %s, DepartmentName is %s, Salary is %d, ZipCode is %d",
				jobDetailRead.getJobTitleId(),jobDetailRead.getJobTitle(),jobDetailRead.getDepartmentName(),
				jobDetailRead.getSalary(),jobDetailRead.getZip());
		System.out.println("-----------------------------------------------------------------------------\n");

		List<JobDetail> jobDetailRead2 = jobDetailDao.getJobDetailByDepartmentName("departmentName");
		for(JobDetail jd : jobDetailRead2) {
			System.out.format("Reading JobDetail: JobTitle is %s, DepartmentName is %s \n",
					jd.getJobTitle(),jd.getDepartmentName());
		}
        System.out.println("-----------------------------------------------------------------------------\n");
		// get edAttainment
		EducationalAttainment edAtRead = edAttainmentDao.getEdAttainmentByEdAttainmentId(1);
		System.out.format("Reading EducationalAttainment: EdAttainmentId is %d, DemographicId is %d, EdType is %s, EdPopulation is %d",
				edAtRead.getEdAttainmentId(),edAtRead.getDemographicId(),edAtRead.getEdType(),edAtRead.getEdPopulation());
		System.out.println("-----------------------------------------------------------------------------\n");

		List<EducationalAttainment> edAtt = edAttainmentDao.getEdAttainmentByEdType(EdType.LESS_THAN_HIGHSCHOOL);
		for(EducationalAttainment eA : edAtt) {
			System.out.format("Reading EducationalAttainment: EdAttainmentId is %d, DemographicId is %d, EdType is %s, EdPopulation is %d"
					, eA.getEdAttainmentId(),eA.getDemographicId(),eA.getEdType(),eA.getEdPopulation());
		}
		System.out.println("-----------------------------------------------------------------------------\n");

		//getAgeDataByAgeId
		AgeData ageDataRead = ageDataDao.getAgeDataByAgeId(1);
		System.out.format("Reading Agedata: DemographicId is %i, AgeRange is %s, agePerntage is %i \n",
				ageDataRead.getDemographicId(), ageDataRead.getAgeRange().value(), ageDataRead.getAgePercentage());
		System.out.println("-----------------------------------------------------------------------------\n");

		//getAgeDataByAgeRange
		List<AgeData> ageDataRead2 = ageDataDao.getAgeDatabyAgeRange(AgeRange.TEN_TO_NINETEEN_YEARS);
		for(AgeData age : ageDataRead2) {
			System.out.format("Reading Agedata: DemographicId is %i, AgeRange is %s, agePercentage is %i \n",
					ageDataRead.getDemographicId(), ageDataRead.getAgeRange().value(), ageDataRead.getAgePercentage());
		}
		System.out.println("-----------------------------------------------------------------------------\n");
		
*/	
		// DELETE
		
/*		userDao.delete(user);
		System.out.println("Deleting User");
		
		neighborhoodDao.delete(neighborhood);
		System.out.println("Deleting Neighborhood");
		
		zipCodeDao.delete(zipCode);
		zipCodeDao.delete(zipCode2);
		System.out.println("Deleting ZipCode");
		
		publicSchoolDao.delete(publicSchool);
		System.out.println("Deleting PublicSchool");
		
		schoolTypeDao.delete(schoolType);
		System.out.println("Deleting SchoolType");
		
		rentDao.delete(rent);
		System.out.println("Deleting Rent");
		
		restaurantDao.delete(restaurant);
		System.out.println("Deleting Restaurant");
		
		ageDataDao.delete(ageData);
		System.out.println("Deleting AgeData");
		
		demographicDao.delete(demographic);
		System.out.println("Deleting Demographic");
		
		ethnicityDao.delete(ethnicity);
		System.out.println("Deleting Ethnicity");
				
		jobDepartmentDao.delete(jobDepartment);
		System.out.println("Deleting Job Department");
		
		jobDetailDao.delete(jobDetail);
		System.out.println("Deleting Job Detail");

		edAttainmentDao.delete(edAttainment);
		System.out.println("Deleting Educational Attainment");
		*/
	}
}
