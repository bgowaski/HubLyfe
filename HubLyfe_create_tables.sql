CREATE SCHEMA IF NOT EXISTS Hublyfe;
USE Hublyfe;



DROP TABLE IF EXISTS Ethnicity;
DROP TABLE IF EXISTS EducationalAttainment;
DROP TABLE IF EXISTS AgeData;
DROP TABLE IF EXISTS Demographics;
DROP TABLE IF EXISTS Restaurants;
DROP TABLE IF EXISTS Rent;
DROP TABLE IF EXISTS JobDetails;
DROP TABLE IF EXISTS JobDepartment;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS SchoolTypes;
DROP TABLE IF EXISTS PublicSchools;
DROP TABLE IF EXISTS ZipCodes;
DROP TABLE IF EXISTS Neighborhoods;





CREATE TABLE Neighborhoods (
	NeighborhoodName VARCHAR(255),
	CONSTRAINT pk_Neighborhoods_NeighborhoodName 
		PRIMARY KEY (NeighborhoodName)
);


CREATE TABLE ZipCodes (
	Zip INT,
    NeighborhoodName VARCHAR(255),
	CONSTRAINT pk_ZipCodes_Zip
		PRIMARY KEY (Zip),
	CONSTRAINT fk_ZipCodes_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
        		REFERENCES Neighborhoods(NeighborhoodName)
        		#ON UPDATE CASCADE ON DELETE SET NULL
);



CREATE TABLE PublicSchools (
SchoolId INT AUTO_INCREMENT,
SchoolName VARCHAR(255),
HistoricalName VARCHAR(255),
AbbreviatedName VARCHAR(255),
Address LONGTEXT,
NeighborhoodName VARCHAR(255),
EducationProgram VARCHAR(255),
YearFounded INT,
YearBuilt INT,
SchoolOpen TIME,
SchoolClose TIME,
SchoolEnrollment VARCHAR(255),
	CONSTRAINT pk_PublicSchools_SchoolId 
		PRIMARY KEY (SchoolId),
	CONSTRAINT fk_PublicSchools_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
        		REFERENCES Neighborhood(NeighborhoodName)
        		ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE SchoolTypes (
	SchoolTypesId INT AUTO_INCREMENT,
	SchoolId INT,
	GradesOffered INT,
	SchoolTypology ENUM("Elementary School", "High School", "Special", "k-8","Middle School"), 
	CONSTRAINT pk_SchoolTypes_SchoolTypesId 
		PRIMARY KEY (SchoolTypesId),
	CONSTRAINT fk_SchoolTypes_SchoolId
		FOREIGN KEY (SchoolId)
        REFERENCES PublicSchools(SchoolId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Users (
	Username VARCHAR(255),
	FirstName VARCHAR(255),
	LastName VARCHAR(255),
	Password VARCHAR(255),
	ResidenceZip INT,
	OccupationZip INT,
	JobTitle VARCHAR(255),
	CONSTRAINT pk_Users_Username 
		PRIMARY KEY (Username)
);


CREATE TABLE JobDepartment (
    DepartmentName VARCHAR(255),
	CONSTRAINT pk_JobDepartment_DepartmentName
		PRIMARY KEY (DepartmentName)
);

CREATE TABLE JobDetails (
	JobTitle VARCHAR(255),
	DepartmentName VARCHAR(255),
	Salary DOUBLE,
	Zip INT,
	CONSTRAINT pk_JobDetails_JobTitle 
		PRIMARY KEY (JobTitle),
	CONSTRAINT fk_JobDetails_Zip
		FOREIGN KEY (Zip)
		REFERENCES ZipCode(Zip)
		ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_JobDetails_DepartmentName
		FOREIGN KEY (DepartmentName)
		REFERENCES JobDepartment(DepartmentName)
		ON UPDATE CASCADE ON DELETE SET NULL
);




CREATE TABLE Rent(
	RentId INT AUTO_INCREMENT,
	NeighborhoodName VARCHAR(255),
	OccupancyType ENUM ("All rentals", "1 Bed", "2 Beds", "3 Beds"),
	Price Double,
	CONSTRAINT pk_Rent_RentId 
		PRIMARY KEY (RentId),
	CONSTRAINT fk_Rent_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
		REFERENCES Neighborhood(NeighborhoodName)
		ON UPDATE CASCADE ON DELETE SET NULL	
);




CREATE TABLE Restaurants (
	RestaurantId INT,
	RestaurantName VARCHAR(255),
	LicenseStatus BOOLEAN,
	RestaurantType ENUM("Eating & Drinking", "Retail Food", "Eating & Drinking w/ Takeout"),
	Address LONGTEXT,
	City VARCHAR(255),
	State VARCHAR(255),
	Zip INT,
	CONSTRAINT pk_Restaurants_RestaurantId 
		PRIMARY KEY (RestaurantId),
	CONSTRAINT fk_Restaurants_Zip
		FOREIGN KEY (Zip)
		REFERENCES ZipCodes(Zip)
		ON UPDATE CASCADE ON DELETE SET NULL
);



CREATE TABLE Demographics (
	DemographicsId INT AUTO_INCREMENT,
	NeighborhoodName VARCHAR (255),
	Decade VARCHAR (255),
	Population DOUBLE,
	Nativity INT,
	FemaleLaborForce INT,
	MaleLaborForce INT,
	OccupiedHousingUnits INT,
	OwnerOccupiedUnits INT,
	RenterOccupiedUnits INT,
	CONSTRAINT pk_Demographics_DemographicsId 
		PRIMARY KEY (DemographicsId),
	CONSTRAINT fk_Demographics_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
		REFERENCES Neighborhood(NeighborhoodName)
		ON UPDATE CASCADE ON DELETE SET NULL	
);

CREATE TABLE EducationalAttainment (
	EdAttainmentId INT AUTO_INCREMENT,
	DemographicsId INT,
	EdType ENUM("less than High school", "High School or GED", "some college or Associate's Degree", "Bachelor's Degree or Higher"),
	EdPopulation INT,	
	UNIQUE (DemographicsId, EdType),
	CONSTRAINT pk_EducationalAttainment_EdAttainmentId 
		PRIMARY KEY (EdAttainmentId),
CONSTRAINT fk_EducationalAttainment_DemographicsId
		FOREIGN KEY (DemographicsId)
		REFERENCES Demographics(DemographicsId)
		ON UPDATE CASCADE ON DELETE CASCADE
);








CREATE TABLE AgeData (
	AgeId INT AUTO_INCREMENT,
	DemographicsId INT,
	AgeRange ENUM("0-9 years", "10-19 years", "20-34 years", "35-54 years", "55-64 years", "65 years and over"),
	AgePercentage INT,
UNIQUE (DemographicsId, AgeRange),
	CONSTRAINT pk_AgeData_AgeId
		PRIMARY KEY (AgeId),
	CONSTRAINT fk_AgeData_DemographicsId
		FOREIGN KEY (DemographicsId)
		REFERENCES Demographics(DemographicsId)
		ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Ethnicity (
	EthnicityId INT AUTO_INCREMENT,
	DemographicsId INT,
	EthnicityType ENUM("white", "black or african american", "hispanic", "asian or PI", "other"),
	EthnicityPopulation INT,
	UNIQUE (DemographicsId, EthnicityType),
	CONSTRAINT pk_Ethnicity_EthnicityId 
		PRIMARY KEY (EthnicityId),
	CONSTRAINT fk_Ethnicity_DemographicsId
		FOREIGN KEY (DemographicsId)
		REFERENCES Demographics(DemographicsId)
		ON UPDATE CASCADE ON DELETE CASCADE
);
