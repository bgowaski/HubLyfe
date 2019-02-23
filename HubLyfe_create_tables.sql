CREATE SCHEMA IF NOT EXISTS Hublyfe;
USE Hublyfe;



DROP TABLE IF EXISTS Ethnicity;
DROP TABLE IF EXISTS EducationalAttainment;
DROP TABLE IF EXISTS AgeData;
DROP TABLE IF EXISTS Demographic;
DROP TABLE IF EXISTS Restaurant;
DROP TABLE IF EXISTS Rent;
DROP TABLE IF EXISTS JobDetail;
DROP TABLE IF EXISTS JobDepartment;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS SchoolType;
DROP TABLE IF EXISTS PublicSchool;
DROP TABLE IF EXISTS ZipCode;
DROP TABLE IF EXISTS Neighborhood;


CREATE TABLE Neighborhood (
	NeighborhoodName VARCHAR(255),
	CONSTRAINT pk_Neighborhood_NeighborhoodName PRIMARY KEY (NeighborhoodName)
);


CREATE TABLE ZipCode (
	Zip INT,
    NeighborhoodName VARCHAR(255),
	CONSTRAINT pk_ZipCodes_Zip PRIMARY KEY (Zip),
	CONSTRAINT fk_ZipCodes_NeighborhoodName FOREIGN KEY (NeighborhoodName)
        		REFERENCES Neighborhood(NeighborhoodName) ON UPDATE CASCADE ON DELETE SET NULL
);



CREATE TABLE PublicSchool(
SchoolId INT AUTO_INCREMENT,
SchoolName VARCHAR(255),
HistoricalName VARCHAR(255),
AbbreviatedName VARCHAR(255),
Address LONGTEXT,
NeighborhoodName VARCHAR(255),
EducationProgram VARCHAR(255),
YearFounded INT,
YearBuilt INT,
SchoolOpen VARCHAR(255),
SchoolClose VARCHAR(255),
SchoolEnrollment VARCHAR(255),
	CONSTRAINT pk_PublicSchool_SchoolId 
		PRIMARY KEY (SchoolId),
	CONSTRAINT fk_PublicSchool_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
        		REFERENCES Neighborhood(NeighborhoodName)
        		ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE SchoolType (
	SchoolTypeId INT AUTO_INCREMENT,
	SchoolId INT,
	GradesOffered VARCHAR(20),
	SchoolTypology ENUM("Elementary School", "High School", "Special", "k-8","Middle School",  "Early Learning"), 
	CONSTRAINT pk_DemographicDemographicsSchoolType_SchoolTypeId 
		PRIMARY KEY (SchoolTypeId),
	CONSTRAINT fk_SchoolType_SchoolId
		FOREIGN KEY (SchoolId)
        REFERENCES PublicSchool(SchoolId)
		ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE User (
	Username VARCHAR(255),
	FirstName VARCHAR(255),
	LastName VARCHAR(255),
	Password VARCHAR(255),
	ResidenceZip INT,
	OccupationZip INT,
	JobTitle VARCHAR(255),
	CONSTRAINT pk_User_Username 
		PRIMARY KEY (Username)
);


CREATE TABLE JobDepartment (
    DepartmentName VARCHAR(255),
	CONSTRAINT pk_JobDepartment_DepartmentName
		PRIMARY KEY (DepartmentName)
);

CREATE TABLE JobDetail (
	JobTitleId INT,
	JobTitle VARCHAR(255),
	DepartmentName VARCHAR(255),
	Salary DECIMAL(10,2),
	Zip INT,
	CONSTRAINT pk_JobDetail_JobTitleId 
		PRIMARY KEY (JobTitleId),
	CONSTRAINT fk_JobDetail_Zip
		FOREIGN KEY (Zip)
		REFERENCES ZipCode(Zip)
		ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_JobDetail_DepartmentName
		FOREIGN KEY (DepartmentName)
		REFERENCES JobDepartment(DepartmentName)
		ON UPDATE CASCADE ON DELETE SET NULL
);

/*Not all zipcodes are present in zipcode table*/


CREATE TABLE Rent(
	RentId INT AUTO_INCREMENT,
	NeighborhoodName VARCHAR(255),
	OccupancyType ENUM ("All rentals", "Studio", "1 Bed", "2 Beds", "3 Beds"),
	Price DECIMAL(6,2),
	CONSTRAINT pk_Rent_RentId 
		PRIMARY KEY (RentId),
	CONSTRAINT fk_Rent_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
		REFERENCES Neighborhood(NeighborhoodName)
		ON UPDATE CASCADE ON DELETE SET NULL	
);



CREATE TABLE Demographic (
	DemographicId INT AUTO_INCREMENT,
	NeighborhoodName VARCHAR (255),
	Population DOUBLE,
	ForiegnBorn INT,
	FemaleLaborForce INT,
	MaleLaborForce INT,
	OccupiedHousingUnits INT,
	OwnerOccupiedUnits INT,
	RenterOccupiedUnits INT,
	CONSTRAINT pk_Demographic_DemographicId 
		PRIMARY KEY (DemographicId),
	CONSTRAINT fk_Demographic_NeighborhoodName
		FOREIGN KEY (NeighborhoodName)
		REFERENCES Neighborhood(NeighborhoodName)
		ON UPDATE CASCADE ON DELETE SET NULL	
);

CREATE TABLE EducationalAttainment (
	EdAttainmentId INT AUTO_INCREMENT,
	DemographicId INT,
	EdType ENUM("less than High school", "High School or GED", "some college or Associate's Degree", "Bachelor's Degree or Higher"),
	EdPopulation INT,	
	UNIQUE (DemographicId, EdType),
	CONSTRAINT pk_EducationalAttainment_EdAttainmentId 
		PRIMARY KEY (EdAttainmentId),
CONSTRAINT fk_EducationalAttainment_DemographicId
		FOREIGN KEY (DemographicId)
		REFERENCES Demographic(DemographicId)
		ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE AgeData (
	AgeId INT AUTO_INCREMENT,
	DemographicId INT,
	AgeRange ENUM("0-9 years", "10-19 years", "20-34 years", "35-54 years", "55-64 years", "65 years and over"),
	AgePercentage INT,
UNIQUE (DemographicId, AgeRange),
	CONSTRAINT pk_AgeData_AgeId
		PRIMARY KEY (AgeId),
	CONSTRAINT fk_AgeData_DemographicId
		FOREIGN KEY (DemographicId)
		REFERENCES Demographic(DemographicId)
		ON UPDATE CASCADE ON DELETE CASCADE
);



CREATE TABLE Ethnicity (
	EthnicityId INT AUTO_INCREMENT,
	DemographicId INT,
	EthnicityType ENUM("White", "Black/ African American", "Hispanic", "Asian/PI", "Other"),
	EthnicityPopulation INT,
	UNIQUE (DemographicId, EthnicityType),
	CONSTRAINT pk_Ethnicity_EthnicityId 
		PRIMARY KEY (EthnicityId),
	CONSTRAINT fk_Ethnicity_DemographicId
		FOREIGN KEY (DemographicId)
		REFERENCES Demographic(DemographicId)
		ON UPDATE CASCADE ON DELETE CASCADE
);

	CREATE TABLE Restaurant (
		RestaurantId INT,
		RestaurantName VARCHAR(255),
		LicenseStatus VARCHAR(255),
		RestaurantType ENUM("Eating & Drinking", "Retail Food", "Eating & Drinking w/ Take Out", "Mobile Food Walk On"),
		Address LONGTEXT,
		City VARCHAR(255),
		State VARCHAR(255),
		Zip INT,
		CONSTRAINT pk_Restaurant_RestaurantId 
			PRIMARY KEY (RestaurantId),
		CONSTRAINT fk_Restaurant_Zip
			FOREIGN KEY (Zip)
			REFERENCES ZipCode(Zip)
			ON UPDATE CASCADE ON DELETE CASCADE
	);



