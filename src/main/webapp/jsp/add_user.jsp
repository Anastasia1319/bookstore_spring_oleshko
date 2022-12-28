<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>You can register in this form!</h1>
<form class="left" action="controller" method="post">
    <input type="hidden" name="command" value="add_user">
    <label>First name<input type="text" name="first_name" placeholder="first name..." required></label><br/>
    <label>Last name<input type="text" name="last_name" placeholder="last name..."></label><br/>
    <label>Email<input type="email" name="email" placeholder="email..." required></label><br/>
    <label>Password<input type="password" name="password" placeholder="password..." minlength="8" required></label><br/>
    <label>Role<input type="text" name="role" placeholder="role..." required></label><br/>
    <input type="submit" value="Register">
</form>
</body>
</html>
