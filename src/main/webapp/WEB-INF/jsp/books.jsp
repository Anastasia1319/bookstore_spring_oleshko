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
    <img src="/static/images/books.jpg" width="700" height="550" alt="Books"/>
    <h1>Books</h1>
    <form action="/books/create" method="get"><input type="submit" value="Add book to catalogue"></form>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Author</th>
            <th>The year of publishing</th>
            <th>Price</th>
            <th>Actions</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.id}</td>
                <td>
                    <a href="/books/${book.id}">${book.title}</a>
                </td>
                <td>${book.author}</td>
                <td>${book.publishinYear}</td>
                <td>${book.price}</td>
                <td>
                    <form action="/books/edit/${book.id}" method="get"><input type="submit" value="Edit"></form>
                </td>
                <td>
                    <form action="/books/delete/${book.id}" method="post"><input type="submit" value="Delete"></form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="paginator.jsp"/>
</div>
</body>
</html>