USE Hublyfe;


INSERT INTO Users(Username, FirstName, LastName, Password, ResidenceZip, OccupationZip, JobTitle)
	VALUES('jgoldst', 'Jeremy', 'Goldstein', 'blackmamba', 2119, '2132', 'Fire Captain'),
    ('sheela27', 'Sheela', 'Satathyanarayana', 'wadlefjkh', '2125', '2155', 'Sr Traffic Engineer'),
    ('gost444', 'Ben', 'Gostkowski', 'hhhtttt', '2368', '2132', 'Spec Library Asst V');
    
LOAD DATA INFILE '/var/mysql/Neighborhoods.csv' INTO TABLE Neighborhoods
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
   
    
LOAD DATA INFILE '/var/mysql/ZipCodes.csv' INTO TABLE ZipCodes
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Restaurants.csv' INTO TABLE Restaurants
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;    
    
LOAD DATA INFILE '/var/mysql/PublicSchools.csv' INTO TABLE PublicSchools
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES; 

    
LOAD DATA INFILE '/var/mysql/SchoolTypes.csv' INTO TABLE SchoolTypes
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
    
LOAD DATA INFILE '/var/mysql/JobDepartment.csv' INTO TABLE JobDepartment
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/JobDetails.csv' INTO TABLE JobDetails
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Rent.csv' INTO TABLE Rent
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Demographics.csv' INTO TABLE Demographics
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/AgeData.csv' INTO TABLE AgeData
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/EducationalAttainment.csv' INTO TABLE EducationalAttainment
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Ethnicity.csv' INTO TABLE Ethnicity
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    

    


