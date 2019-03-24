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
	<h3>Search for User by FirstName</h3>
	</div>
	<div class="panel-body">
	<form action="finduser" method="post">
		
		<p class="form-group">
			<label for="firstname">FirstName</label>
			<input class="form-control" id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input class="btn btn-primary" type="submit">
			<br/><br/><br/>
			<span class="alert alert-info" id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>

	<h4>Matching Users</h4>
        <table class="table table-striped">
        <thead>
            <tr>
                <th>UserName</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date of Birth</th>
                <th>Rentals </th>
                <th>Demographics </th>
                <th>Public Schools </th>
                <th>Delete User</th>
                <th>Update User</th>
                
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${user}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><fmt:formatDate value="${user.getDob()}" pattern="yyyy-MM-dd"/></td>
                    <td><a href="userrentals?username=<c:out value="${user.getUserName()}"/>">Rentals</a></td>
                    <td><a href="userdemographics?username=<c:out value="${user.getUserName()}"/>">Demographics</a></td>
                    <td><a href="userschools?username=<c:out value="${user.getUserName()}"/>">Schools</a></td>
                  	<td><a href="deleteuser?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="updateuser?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
            </c:forEach>
            </tbody>
       </table>
       </div>
       </div>
</body>
</html>
