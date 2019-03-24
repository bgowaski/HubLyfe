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
		<h3>Search for Job Details by Title</h3>
	</div>
	<div class="panel-body">
	<form action="searchjobs" method="post">
		
		<p class="form-group">
			<label for="jobtitle">Title like</label>
			<input class="form-control" id="jobtitle" name="jobtitle" value="${fn:escapeXml(param.jobtitle)}">
		</p>
		<p>
			<input class="btn btn-primary"  type="submit">
			<br/><br/><br/>
			<a class="alert alert-info" id="successMessage"><b>${messages.success}</b></a>
		</p>
	</form>
	<br/>
	
	<h4>Matching Jobs</h4>
        <table class="table table-striped" >
        <thead>
            <tr>
               
                <th>Title</th>
                <th>Salary</th>
                <th>JobDepartment</th>
                
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${jobs}" var="jobs" >
                <tr>
                    <td><c:out value="${jobs.getJobTitle()}" /></td>
                    <td><c:out value="${jobs.getSalary()}" /></td>
                  	 <td><c:out value="${jobs.getJobDepartment()}" /></td>
                  
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
