<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="/static/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>You can edit user's personal information here!</h1>
<form class="left" action="/users/edit/${user.id}" method="post">
    <label>First name<input type="text" name="first_name" value="${user.firstName}" required></label><br/>
    <label>Last name<input type="text" name="last_name" value="${user.lastName}"></label><br/>
    <label>Email<input type="email" name="email" value="${user.email}" required></label><br/>
    <label>Password<input type="password" name="password" value="${user.password}" minlength="8" required></label><br/>
    <label>Admin
        <c:if test="${user.role == ADMIN}">
            <input type="radio" id="ADMIN" name="role" value="ADMIN" checked>
        </c:if>
        <input type="radio" id="ADMIN" name="role" value="ADMIN">
    </label>
    <label>Manager
        <c:if test="${user.role == MANAGER}">
            <input type="radio" id="MANAGER" name="role" value="MANAGER" checked>
        </c:if>
        <input type="radio" id="MANAGER" name="role" value="MANAGER">
    </label>
    <label>Customer
        <c:if test="${user.role == CUSTOMER}">
            <input type="radio" id="CUSTOMER" name="role" value="CUSTOMER" checked>
        </c:if>
        <input type="radio" id="CUSTOMER" name="role" value="CUSTOMER">
    </label><br/>
    <input type="submit" value="Save">
</form>
</body>
</html>