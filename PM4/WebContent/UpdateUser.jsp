<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1 <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>Update User</h1>
	<h1>${messages.success}</h1>
	<form action="updateuser" method="post">
		<p <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<label for="lastname">New LastName</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
		</p>
	</form>
	<br/><br/>

</body>
</html>