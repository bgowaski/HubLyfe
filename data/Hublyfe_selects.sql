SELECT Restaurant.RestaurantName, User.UserName
FROM Restaurant 
INNER JOIN User on User.ResidenceZip = Restaurant.Zip
WHERE Restaurant.LicenseStatus = "Active";

SELECT Demographic.NeighborhoodName, Rent.Price, Demographic.Population
FROM Demographic
INNER JOIN Demographic on Rent.NeighborhoodName = Demographic.NeighborhoodName
ORDER BY(Demographic.RenterOccupiedUnits) LIMIT 1
