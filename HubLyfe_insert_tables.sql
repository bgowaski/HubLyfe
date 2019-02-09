USE Hublyfe;


INSERT INTO Users(Username, FirstName, LastName, Password, ResidenceZip, OccupationZip, JobTitle)
	VALUES('jgoldst', 'Jeremy', 'Goldstein', 'blackmamba', 2119, '2132', 'Fire Captain'),
    ('sheela27', 'Sheela', 'Satathyanarayana', 'wadlefjkh', '2125', '2155', 'Sr Traffic Engineer'),
    ('gost444', 'Ben', 'Gostkowski', 'hhhtttt', '2368', '2132', 'Spec Library Asst V');
    
LOAD DATA INFILE '/var/mysql/Neighborhoods.csv' INTO TABLE Neighborhoods
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n';
   
    
LOAD DATA INFILE '/var/mysql/ZipCodes.csv' INTO TABLE ZipCodes
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/JobDepartment.csv' INTO TABLE JobDepartment
	FIELDS TERMINATED BY ','
    LINES TERMINATED BY ',\n'
    IGNORE 1 LINES;
    
LOAD DATA INFILE '/var/mysql/JobDepartment.csv' INTO TABLE JobDepartment
    LINES TERMINATED BY ','
    IGNORE 1 LINES;
    
INSERT INTO JobDepartment(DepartmentName)
VALUES('POLICE DEPartment')
    

INSERT INTO ZipCodes(Zip, NeighborhoodName)
VALUES('02134', 'Allston')

