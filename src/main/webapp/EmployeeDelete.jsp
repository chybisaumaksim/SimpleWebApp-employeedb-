<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/myStyle.css"%>
</style>
<style>
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
                <br><a href="${pageContext.request.contextPath}/main?GetById">Get employee</a></br>
            </div>
        </form>
    </div>
</div>
<script>
    <%@include file="/WEB-INF/js/index.js"%>
</script>
<form action="<c:url value="/main"/>" method="POST">
<b><% if (request.getAttribute("messageDelete") != null) { %>
    <div id="success" class="message">
        <%=request.getAttribute("messageDelete")%>
    </div>
    <% } %>
</b>
    <table width="100%" cellpadding="1" cellspacing="1">
        <tr>
            <td></td>
            <td>Name</td>
            <td>Surname</td>
            <td>Department ID</td>
            <td>Job title</td>
            <td>Gender</td>
            <td>Date of birth</td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${employee}" var="employee">
            <tr>
                <td><input type="radio" name="id" value="${employee.employeeId}"></td>
                <td><c:out value="${employee.firstName}"/></td>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.departmentId}"/></td>
                <td><c:out value="${employee.jobTitle}"/></td>
                <td><c:out value="${employee.gender}"/></td>
                <td><c:out value="${employee.dateOfBirth}"/></td>
                <td><input type="submit" value="Delete" name="Delete"/></td>
                <td><input type="submit" value="Edit" name="Update"/></td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
