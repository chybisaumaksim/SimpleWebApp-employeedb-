<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update employee</title>
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
                <br><a href="${pageContext.request.contextPath}/main?Update">Edit of employee</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Create new employee</a></br>
                <br><a href="${pageContext.request.contextPath}/main?getById">Get employee</a></br>
            </div>
        </form>
    </div>
</div>
<script>
    <%@include file="/WEB-INF/js/index.js"%>
</script>
<form action="<c:url value="/main"/>" method="POST">
    <% if (request.getAttribute("messageSuccess") != null) { %>
    <div id="success" class="message">
        <%=request.getAttribute("messageSuccess")%>
        <% } %>
    </div>
    <input type="hidden" name="id" value="${employee.employeeId}"/>
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="firstName" value="${employee.firstName}" required
                       pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input type="text" name="lastName" value="${employee.lastName}" required
                       pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/>
            </td>
        </tr>
        <tr>
            <td>Department ID:</td>
            <td><input type="text" name="departmentId" value="${employee.departmentId}" required pattern="^[0-9]+$"/>
            </td>
        </tr>
        <tr>
            <td>Job Title:</td>
            <td><input type="text" name="jobTitle" value="${employee.jobTitle}" required
                       pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/>
            </td>
        </tr>
        <tr>
            <td>Gender:</td>
            <td><input type="text" name="gender" value="${employee.gender}" required pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/>
            </td>
        </tr>
        <tr>
            <td>Date of Birth:</td>
            <td><input type="text" name="dateOfBirth" value="${employee.dateOfBirth}" required
                       pattern="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$"/></td>
            <% if (request.getAttribute("messageForBirthDate") != null) { %>
            <td>
                <div id="error3" class="text">
                    <%=request.getAttribute("messageForBirthDate")%>
                    <% } %>
                </div>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" value="Update" name="Update"/></td>
        </tr>
    </table>
</form>
</body>
</html>