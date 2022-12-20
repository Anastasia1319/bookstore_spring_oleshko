<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div class="center">
      <img src="jpg/user.jpg" alt="User" />
      <h1>User</h1>
      <h2>Id</h2>
      <p>${requestScope.user.id}</p>
      <h2>First Name</h2>
      <p>${requestScope.user.firstName}</p>
      <h2>Last Name</h2>
      <p>${requestScope.user.lastName}</p>
      <h2>Email</h2>
      <p>${requestScope.user.email}</p>
    </div>
  </body>
</html>