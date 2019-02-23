USE Hublyfe;


INSERT INTO Users(Username, FirstName, LastName, Password, ResidenceZip, OccupationZip, JobTitle)
	VALUES('jgoldst', 'Jeremy', 'Goldstein', 'blackmamba', 2119, '2132', 'Fire Captain'),
    ('sheela27', 'Sheela', 'Satathyanarayana', 'wadlefjkh', '2125', '2155', 'Sr Traffic Engineer'),
    ('gost444', 'Ben', 'Gostkowski', 'hhhtttt', '2368', '2132', 'Spec Library Asst V');
    
LOAD DATA INFILE '/var/mysql/Neighborhood.csv' INTO TABLE Neighborhood
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
   
    
LOAD DATA INFILE '/var/mysql/ZipCode.csv' INTO TABLE ZipCode
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Restaurant.csv' INTO TABLE Restaurant
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;    
    
LOAD DATA INFILE '/var/mysql/PublicSchool.csv' INTO TABLE PublicSchool
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES; 

    
LOAD DATA INFILE '/var/mysql/SchoolType.csv' INTO TABLE SchoolTypes
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
    
LOAD DATA INFILE '/var/mysql/JobDepartment.csv' INTO TABLE JobDepartment
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/JobDetail.csv' INTO TABLE JobDetail
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Rent.csv' INTO TABLE Rent
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/Demographic.csv' INTO TABLE Demographic
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
    

    


