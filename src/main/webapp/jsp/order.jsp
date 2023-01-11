<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <img src="jpg/order.jpg" alt="Order" />
    <h1>Order</h1>
    <h2>Id</h2>
    <p>${requestScope.order.id}</p>
    <h2>Customer</h2>
    <p>${requestScope.order.user}</p>
    <h2>Your order:</h2>
    <table>
        <thead>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Quantity</th>
        </thead>
        <tbody>
        <c:forEach var="items" items="${requestScope.order.items}">
            <tr>
                <td>
                    <a href="controller?command=book&id=${items.book.id}">${items.book.title}</a>
                </td>
                <td>${items.book.author}</td>
                <td>${items.book.price}</td>
                <td>${items.price}</td>
                <td>${items.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>Total cost: ${requestScope.order.totalCost}</h2>
</div>
</body>
</html>
