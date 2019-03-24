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
<title>Find Restaurants</title>
</head>
<body>
<div class="panel panel-primary">
	<a href="/#">&#8678; Back</a>
	<div class="panel-heading">
		<h3>Search for Restaurants by Type</h3>
	</div>
	</div>
	<div class="panel-body">
	
	<form action="searchrestaurantsbytype" method="post">
		<p class="form-group">
			<label for=restauranttype>Please Type in Selection From List Below</label>
			<input class="form-control" id="restauranttype" name="restauranttype" value="${fn:escapeXml(param.restauranttype)}">
			<h6>Eating & Drinking</h6>
			<h6>Retail Food</h6>
			<h6>Eating & Drinking w/ Take Out</h6>
			<h6>Mobile Food Walk On</h6>
		</p>
		<p>
			<input class="btn btn-primary"  type="submit">
			<br/><br/><br/>
			<a class="alert alert-info" id="successMessage"><b>${messages.success}</b></a>
		</p>
	</form>
	<br/>
	
	<h4>Matching Restaurants by Type Selected</h4>
        <table class="table table-striped" >
        <thead>
            <tr>
               
                <th>Restaurant Name</th>
                <th>Restaurant Type</th>
                <th>Restaurant Address</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${restaurants}" var="restaurants" >
                <tr>
                    <td><c:out value="${restaurants.getRestaurantName()}" /></td>
                    <td><c:out value="${restaurants.getRestaurantType()}" /></td>
                    <td><c:out value="${restaurants.getAddress()}" /></td>
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
