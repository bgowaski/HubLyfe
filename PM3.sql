USE Hublyfe;

#1. When a user moves to a city they may want to know where most renters live, how many people live in that neighborhood, and what the average rent is of that neighborhood.

SELECT Demographic.NeighborhoodName, Demographic.Population, (Demographic.RenterOccupiedUnits/Demographic.OccupiedHousingUnits)*100 AS '% Renter occupied',  Rent.Price
FROM Demographic
INNER JOIN Rent on Rent.NeighborhoodName = Demographic.NeighborhoodName
WHERE Rent.OccupancyType = 'All rentals'
ORDER BY((Demographic.RenterOccupiedUnits/Demographic.OwnerOccupiedUnits)*100) DESC LIMIT 1;

#2. If a family is moving to a new area and they have young kids, they would want to move to a neighborhood with the newest elementary school.

SELECT PublicSchool.NeighborhoodName, PublicSchool.SchoolName, SchoolType.SchoolTypology
FROM PublicSchool
LEFT OUTER JOIN SchoolType on PublicSchool.SchoolId = SchoolType.SchoolId
HAVING SchoolType.SchoolTypology = 'Elementary School'
ORDER BY (PublicSchool.YearBuilt) DESC LIMIT 1;

#3. If a user knows where they are going to live (neighborhood/zip) they will want to know all active restaurants in their area.

SELECT User.UserName,Restaurant.RestaurantType, Restaurant.RestaurantName, Restaurant.LicenseStatus
FROM Restaurant 
INNER JOIN User on User.ResidenceZip = Restaurant.Zip
WHERE Restaurant.LicenseStatus = "Active" 
#AND UserName = 'gost444'
ORDER BY UserName, RestaurantType, RestaurantName;


#4. A young professional would want to know which neighborhood has the most people their age and are also college educated so that they are like minded.

SELECT Demographic.NeighborhoodName, Demographic.Population AS 'Total Population', AgeData.AgePercentage AS 'Population of User''s Age', EducationalAttainment.EdPopulation AS 'Population of degree holders'
FROM Demographic
LEFT OUTER JOIN AgeData ON Demographic.DemographicId = AgeData.DemographicId
LEFT OUTER JOIN EducationalAttainment ON Demographic.DemographicId = EducationalAttainment.DemographicId
WHERE EdType = 'some college or Associate''s Degree' AND AgeData.AgeRange = (SELECT 
	CASE
		WHEN (User.Age REGEXP '^([0-9])$') THEN '0-9 years'
        WHEN (User.Age REGEXP '^(1[0-9])$') THEN '10-19 years'
        WHEN (User.Age REGEXP '^(2[0-9]|3[0-4])$') THEN '20-34 years'
        WHEN (User.Age REGEXP '^(3[5-9]|4[0-9]|5[0-4])$') THEN '35-54 years'
        WHEN (User.Age REGEXP '^(5[5-9]|6[0-4])$') THEN '55-64 years'
		ELSE '65 years and over'
	END AS AgeRange
FROM User
WHERE User.UserName = 'sheela27')
ORDER BY AgeData.AgePerJobDetailcentage DESC, EdPopulation DESC LIMIT 1;


#5. A teacher or a fire captain is moving to boston and wants to know the salary, department (school), and neighborhoods that this job is offered. 

SELECT DISTINCT ZipCode.NeighborhoodName, JobDetail.Zip, DepartmentName, JobTitle, Salary 
FROM JobDetail 
LEFT OUTER JOIN  ZipCode ON JobDetail.Zip = ZipCode.Zip
WHERE JobTitle like 'Fire Captain%'
ORDER BY  NeighborhoodName, Salary DESC,  DepartmentName , JobDetail.Zip;

#6. Based on average age percentage in a neighborhood of interest, the user can find how many schools are available for that age range.



#7. Busy users who depend on take out food for most of their meals would like to know top 5 neighborhoods have the most take out restaurants.
SELECT ZipCode.NeighborhoodName,Restaurant.RestaurantType, Restaurant.LicenseStatus, Count(*) AS 'Total Take Out Restaurants'
FROM Restaurant
LEFT OUTER JOIN ZipCode ON Restaurant.Zip = ZipCode.Zip
GROUP BY ZipCode.NeighborhoodName, Restaurant.RestaurantType, Restaurant.LicenseStatus
HAVING Restaurant.RestaurantType = 'Eating & Drinking w/ Take Out' AND Restaurant.LicenseStatus = 'Active'
ORDER BY  Count(*) DESC, ZipCode.NeighborhoodName 
LIMIT 5;

#8. A rich single person wants to move to the most expensive neighborhood and live in a studio or 1 bedroom with the highest rent.

SELECT * FROM ((SELECT NeighborhoodName, OccupancyType, Price FROM Rent
WHERE OccupancyType in ('1 Bed')
ORDER BY Price DESC LIMIT 1)
UNION 
(SELECT NeighborhoodName, OccupancyType, Price FROM Rent
WHERE OccupancyType in ('Studio')
ORDER BY Price DESC LIMIT 1)) AS ExpensiveAccomodation;

#9. A user trying to switch careers to something more popular may want to know which job departments in the city have the most jobs.



#10. A foreign born retired (65+) hispanic couple wants to move to a neighborhood with similar people to be able to make more friends. 
# - Age percentage, foreign born percentage (foreign born/pop), for every neighborhood containing hispanic type.

SELECT NeighborhoodName, Population, ROUND( (ForiegnBorn/Population)*100) AS '% Foriegn Born', 
	ROUND((EthnicityPopulation/Population)*100) AS '% Hispanic', ROUND((AgePercentage/Population)*100) AS '% 65+'
FROM Demographic
LEFT OUTER JOIN Ethnicity ON Demographic.DemographicId = Ethnicity.DemographicId
LEFT OUTER JOIN AgeData ON Demographic.DemographicId = AgeData.DemographicId
WHERE EthnicityType = 'Hispanic' AND AgeData.AgeRange = '65 years and over'
ORDER BY (AgePercentage/Population)*100 DESC,(EthnicityPopulation/Population)*100 DESC, (ForiegnBorn/Population)*100 DESC