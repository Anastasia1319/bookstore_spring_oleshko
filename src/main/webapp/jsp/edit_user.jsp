<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>You can edit user's personal information here!</h1>
<form class="left" action="controller" method="post">
    <input type="hidden" name="command" value="edit_user">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <label>First name<input type="text" name="first_name" value="${requestScope.user.firstName}" required></label><br/>
    <label>Last name<input type="text" name="last_name" value="${requestScope.user.lastName}"></label><br/>
    <label>Email<input type="email" name="email" value="${requestScope.user.email}" required></label><br/>
    <label>Password<input type="password" name="password" value="${requestScope.user.password}" minlength="8" required></label><br/>
    <label>Admin<input type="radio" id="ADMIN" name="role" value="ADMIN"></label>
    <label>Manager<input type="radio" id="MANAGER" name="role" value="MANAGER"></label>
    <label>Customer<input type="radio" id="CUSTOMER" name="role" value="CUSTOMER"></label><br/>
    <input type="submit" value="Save">
</form>
</body>
</html>