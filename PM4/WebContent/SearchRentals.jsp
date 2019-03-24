<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a User</title>
</head>
<body>
	<div class="panel panel-primary">
	<a href="/#">&#8678; Back</a>
	<div class="panel-heading">
		
		<h3>Search for Rentals by Neighborhood</h3>
	</div>
	<div class="panel-body">
	<form action="searchrentals" method="post">
		<p class="form-group">
			<label for="neighborhoodname">Neighborhood</label>
			<input class="form-control" id="neighborhoodname" name="neighborhoodname" value="${fn:escapeXml(param.neighborhoodname)}">
		</p>
		<p>
			<input class="btn btn-primary" type="submit">
			<br/><br/><br/>
			<span class="alert alert-info" id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<h4>Matching Rentals</h4>
        <table class="table table-striped">
        <thead>
            <tr>
                <th>Occupancy Type</th>
                <th>Price</th>
               
            </tr>
          </thead> 
          <tbody>
            <c:forEach items="${rents}" var="rents" >
                <tr>
                    
                    <td><c:out value="${rents.getOccupancyType().value()}" /></td>
                    <td><c:out value="${rents.getPrice()}" /></td>
              
                </tr>
            </c:forEach>
            </tbody>
       </table>
       </div>
       </div>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
