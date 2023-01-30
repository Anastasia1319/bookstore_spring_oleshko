<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <h1>You can edit book here!</h1>
    <form class="left" action="/books/edit/${book.id}" method="post">
        <label>Title<input type="text" name="title" value="${book.title}"></label><br/>
        <label>Author<input type="text" name="author" value="${book.author}"></label><br/>
        <label>Publishing year<input type="text" name="publishing_year" value="${book.publishingYear}"></label><br/>
        <label>Isbn<input type="text" name="isbn" value="${book.isbn}" minlength="13" maxlength="13" required></label><br/>
        <input type="submit" value="Save">
    </form>
</body>
</html>
