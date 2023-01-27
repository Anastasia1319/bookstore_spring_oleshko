<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="/static/css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <jsp:include page="navbar.jsp"/>
    <div class="center">
      <img src="/static/images/book.jpg" alt="Book" />
      <h1>Book</h1>
      <h2>Id</h2>
      <p>${book.id}</p>
      <h2>Title</h2>
      <p>${book.title}</p>
      <h2>Author</h2>
      <p><a href="/users/find/${book.author}/page=0" ${book.author}</p>
      <h2>The year of publishing</h2>
      <p>${book.publishingYear}</p>
      <h2>ISBN</h2>
      <p>${book.isbn}</p>
      <h2>Price</h2>
      <p>${book.price}</p>
    </div>
  </body>
</html>