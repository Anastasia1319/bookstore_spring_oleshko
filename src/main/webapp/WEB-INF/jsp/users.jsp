<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>BookStore</title>
    <link href="/static/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="center">
    <img src="/static/images/users.jpg" width="700" height="550" alt="Users"/>
    <h1>Users</h1>
    <form action="/users/create" method="get"><input type="submit" value="User registration"></form>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>
                    <a href="/users/${user.id}">${user.firstName} ${user.lastName}</a>
                </td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>
                    <form action="/users/edit/${user.id}" method="get"><input type="submit" value="Edit"></form>
                </td>
                <td>
                    <form action="/users/delete/${user.id}" method="post"><input type="submit" value="Delete"></form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="paginator.jsp"/>
</div>
</body>
</html>