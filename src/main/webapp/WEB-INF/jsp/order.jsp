<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BookStore</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="center">
    <img src="/images/order.jpg" alt="Order" />
    <h1>Order</h1>
    <h2>Id</h2>
    <p>${order.id}</p>
    <h2>Customer</h2>
    <p>${order.user.firstName} ${order.user.lastName}</p>
    <h2>Your order:</h2>
    <p>Status: ${order.status}</p>
    <table>
        <thead>
            <th>Title</th>
            <th>Author</th>
            <th>Price</th>
            <th>Quantity</th>
        </thead>
        <tbody>
        <c:forEach var="item" items="${order.items}">
            <tr>
                <td>
                    <a href="/books/${item.book.id}">${item.book.title}</a>
                </td>
                <td>${item.book.author}</td>
                <td>${item.book.price}</td>
                <td>${item.quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <h2>Total cost: ${order.totalCost}</h2>
</div>
</body>
</html>
