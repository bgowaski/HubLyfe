<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="searchjobs" method="post">
		<h1>Search for a Job Details by Title</h1>
		<p>
			<label for="jobtitle">Title like</label>
			<input id="jobtitle" name="jobtitle" value="${fn:escapeXml(param.jobtitle)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	
	<h1>Matching Jobs</h1>
        <table border="1">
            <tr>
               
                <th>Title</th>
                <th>Salary</th>
                 
                
            </tr>
            <c:forEach items="${jobs}" var="jobs" >
                <tr>
                    <td><c:out value="${jobs.getJobTitle()}" /></td>
                    <td><c:out value="${jobs.getSalary()}" /></td>
                  
                </tr>
            </c:forEach>
       </table>
</body>
</html>
