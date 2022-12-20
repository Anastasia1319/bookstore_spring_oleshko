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
      <img src="jpg/books.jpg" width="700" height="550" alt="Books" />
      <h1>Books</h1>
      <table>
        <thead>
            <th>Id</th><th>Title</th><th>Author</th><th>Price</th>
        </thead>
        <tbody>
        <c:forEach var="book" items="${requestScope.books}">
            <tr>
                <td>${book.id}</td><td>${book.title}</td><td>${book.author}</td><td>${book.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </div>
  </body>
</html>