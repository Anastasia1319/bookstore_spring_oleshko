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
      <img src="jpg/book.jpg" alt="Book" />
      <h1>Book</h1>
      <h2>Id</h2>
      <p>${requestScope.book.id}</p>
      <h2>Title</h2>
      <p>${requestScope.book.title}</p>
      <h2>Author</h2>
      <p>${requestScope.book.author}</p>
    </div>
  </body>
</html>