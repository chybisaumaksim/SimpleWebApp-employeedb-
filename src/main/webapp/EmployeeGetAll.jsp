<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Get all employees </title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/myStyle.css"%>
    <%@include file="/WEB-INF/css/menuStyle.css"%>
</style>
<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Employees
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown">
                <br><a href="${pageContext.request.contextPath}/main?getAll">List of employees</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Edit of employee</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Create new employee</a></br>
                <br><a href="${pageContext.request.contextPath}/main?getById">Get employee</a></br>
            </div>
        </form>
    </div>
</div>
<script>
    <%@include file="/WEB-INF/js/index.js"%>
</script>
<table width="100%" cellpadding="1" cellspacing="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Surname</td>
        <td>Department ID</td>
        <td>Job title</td>
        <td>Gender</td>
        <td>Date of birth</td>
    </tr>
    <c:forEach items="${employee}" var="employee">
        <tr>
            <td>${employee.employeeId}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.departmentId}</td>
            <td>${employee.jobTitle}</td>
            <td>${employee.gender}</td>
            <td>${employee.dateOfBirth}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
