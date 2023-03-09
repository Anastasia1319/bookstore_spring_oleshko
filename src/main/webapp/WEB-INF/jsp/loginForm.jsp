<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>BookStore</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Welcome back!</h1>
<form class="left" action="/login" method="post">
    <label>Email: <input name="email" type="email"></label><br>
    <label>Password: <input name="password" type="password" minlength="8"></label>
    <button>Login</button>
</form>
</body>
</html>
