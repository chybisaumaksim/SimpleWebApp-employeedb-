<%@page contentType="text/html;charset=UTF-8" %>
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
                <br><a href="${pageContext.request.contextPath}/main?getById">Get employee</a></br>
            </div>
        </form>
    </div>
</div>

