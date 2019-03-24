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
<title>Create a User</title>
</head>
<body>
	<div class="panel panel-primary">
	<a href="/#">&#8678; Back</a>
	<div class="panel-heading">
	<h4>Create User</h4>
	</div>
	<div class="panel-body">
	<h4 class="alert alert-success" <c:if test="${!messages.disableSubmit}">style="display:none"</c:if>>${messages.success}</h4>
	<form action="createuser" method="post">
		<div  class="form-group" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="username">UserName</label>
			<input class="form-control" id="username" name="username" value="">
		</div>
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="firstname">FirstName</label>
			<input class="form-control" id="firstname" name="firstname" value="">
		</div>
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="lastname">LastName</label>
			<input class="form-control" id="lastname" name="lastname" value="">
		</div>
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="password">Password</label>
			<input class="form-control" id="passowrd" name="passowrd" value="">
		</div>
		<div  class="form-group" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="dob">DoB (yyyy-mm-dd)</label>
			<input class="form-control" id="dob" name="dob" value="">
		</div>
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for=residencezip>Zip of Residence Location </label>
			<input class="form-control" id="residencezip" name="residencezip" value="">
		</div>
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for=occupationzip>Zip of Occupation Location </label>
			<input class="form-control" id="occupationzip" name="occupationzip" value="">
		</div>		
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for=jobtitle>Preferred Job Title</label>
			<input class="form-control" id="jobtitle" name="jobtitle" value="">
		</div>		
		<div  class="form-group"  <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input class="btn btn-primary" type="submit">
		</div>
	</form>
	<br/><br/>
	</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>