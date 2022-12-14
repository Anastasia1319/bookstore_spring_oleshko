<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>BookStore</title>
        <meta charset="UTF-8">
    </head>

    <body>
        <h1>Book</h1>
        <h2>Id</h2>
        <p>${requestScope.book.id}</p>
        <h2>Title</h2>
        <p>${requestScope.book.title}</p>
        <h2>Author</h2>
        <p>${requestScope.book.author}</p>
    </body>
</html>