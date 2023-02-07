<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>BookStore</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="center">
    <jsp:include page="navbar.jsp"/>
    <img src="/images/welcome.jpg" alt="Welcome"/>
    <h1>Hello!</h1>
    <p>We are glad to welcome you to our bookstore!</p>
</div>
<ul>
    <c:if test="${sessionScope.user !=null}">
        <p class="center">Hello, ${sessionScope.user.firstName} ${sessionScope.user.lastName}. Can you share your plans
            to take over the world?</p>
        <li><a href="/logout">Logout</a></li>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <p class="center">Demons are full of ideas! If you would like to submit your own, please register or login!</p>
        <li><a href="/users/create">Register</a></li>
        <li><a href="/login">Login</a></li>
    </c:if>
</ul>
</body>
</html>