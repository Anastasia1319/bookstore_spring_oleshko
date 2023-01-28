<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="/static/css/style.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <div class="center">
      <img src="/static/images/error.jpg" alt="Error" />
      <h1 class="error">Error</h1>
      <h2 class="error">Everything is broken!</h2>
      <div>${message != null ? message : "Do not worry, we will fix it soon!"}</div>
    </div>
  </body>
</html>