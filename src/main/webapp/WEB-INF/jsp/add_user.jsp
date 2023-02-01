<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>BookStore</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>You can register in this form!</h1>
<form class="left" action="/users/create" method="post">
    <label>First name<input type="text" name="firstName" placeholder="first name..." required></label><br/>
    <label>Last name<input type="text" name="lastName" placeholder="last name..."></label><br/>
    <label>Email<input type="email" name="email" placeholder="email..." required></label><br/>
    <label>Password<input type="password" name="password" placeholder="password..." minlength="8" required></label><br/>
    <label>Admin<input type="radio" id="ADMIN" name="role" value="ADMIN"></label>
    <label>Manager<input type="radio" id="MANAGER" name="role" value="MANAGER"></label>
    <label>Customer<input type="radio" id="CUSTOMER" name="role" value="CUSTOMER" checked></label><br/>
    <input type="submit" value="Register">
</form>
</body>
</html>
