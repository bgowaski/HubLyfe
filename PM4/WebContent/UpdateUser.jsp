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
<title>Update a User</title>
</head>
<body>
	<div class="panel panel-primary">
	<a href="/#">&#8678; Back</a>
	<div class="panel-heading">
	<h3 <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>Update User</h3>
	</div>
	<div class="panel-body">
	<h3>${messages.success}</h3>
	<form action="updateuser" method="post">
		<p class="form-group" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="username">UserName</label>
			<input class="form-control" id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p class="form-group" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="lastname">New LastName</label>
			<input class="form-control" id="lastname" name="lastname" value="">
		</p>
		<p <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input class="btn btn-primary" type="submit">
		</p>
	</form>
	<br/><br/>
	</div>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>