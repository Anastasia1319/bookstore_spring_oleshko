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
    <h1>You can add book here!</h1>
    <form class="left" action="controller" method="post">
        <input type="hidden" name="command" value="add_book">
        <label>Title<input type="text" name="title" placeholder="title..."></label><br/>
        <label>Author<input type="text" name="author" placeholder="author..."></label><br/>
        <label>Publishing year<input type="text" name="publishing_year" placeholder="publishing year..."></label><br/>
        <label>Isbn<input type="text" name="isbn" placeholder="isbn..." minlength="13" maxlength="13" required></label><br/>
        <label>Price<input type="text" name="price" placeholder="price..."></label><br/>
        <input type="submit" value="Creat book">
    </form>
</body>
</html>
