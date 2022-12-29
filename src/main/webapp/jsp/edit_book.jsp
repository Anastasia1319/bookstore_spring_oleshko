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
    <h1>You can edit book here!</h1>
    <form class="left" action="controller" method="post">
        <input type="hidden" name="command" value="edit_book">
        <input type="hidden" name="id" value="${requestScope.book.id}">
        <label>Title<input type="text" name="title" value="${requestScope.book.title}"></label><br/>
        <label>Author<input type="text" name="author" value="${requestScope.book.author}"></label><br/>
        <label>Publishing year<input type="text" name="publishing_year" value="${requestScope.book.publishinYear}"></label><br/>
        <label>Isbn<input type="text" name="isbn" value="${requestScope.book.isbn}" minlength="13" maxlength="13" required></label><br/>
        <input type="submit" value="Save">
    </form>
</body>
</html>
