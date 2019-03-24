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
<title>Job Details</title>
</head>
<body>
	<div class="panel panel-primary">
	<a href="/#">&#8678; Back</a>
	<div class="panel-heading">
	<h3>${messages.title}</h3>
	</div>
 <div class="panel-body">
        <table class="table table-striped">
        <thead>
            <tr>
                <th>Job Title</th>
                <th>Salary</th>
                <th>Job Department</th>
               
               
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${jobDetail}" var="jobDetail" >
                <tr>
                    <td><c:out value="${jobDetail.getJobTitle}" /></td>
                    <td><c:out value="${jobDetail.getSalary}" /></td>
                    <td><c:out value="${jobDetail.getJobDepartment()}" /></td>
                   
              
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