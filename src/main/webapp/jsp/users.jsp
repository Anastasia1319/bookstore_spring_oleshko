<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <jsp:include page="navbar.jsp"/>
    <div class="center">
      <img src="jpg/users.jpg" width="700" height="550" alt="Users" />
      <h1>Users</h1>
      <a href="controller?command=add_user_form">User registration</a>
      <table>
        <thead>
            <th>Id</th><th>Name</th><th>Email</th><th>Role</th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.id}</td><td>${user.userName}</td><td>${user.email}</td><td>${user.role}</td>
            </tr>
        </c:forEach>
        </tbody>
    </div>
  </body>
</html>