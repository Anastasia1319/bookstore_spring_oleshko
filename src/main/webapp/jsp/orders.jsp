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
    <img src="jpg/orders.jpg" width="700" height="550" alt="Orders" />
    <h1>Orders</h1>
    <table>
      <thead>
        <th>Id</th>
        <th>User</th>
        <th>Total cost</th>
        <th>Status</th>
      </thead>
      <tbody>
      <c:forEach var="order" items="${requestScope.orders}">
        <tr>
          <td>
            <a href="controller?command=order&id=${order.id}">${order.id}</a>
         </td>
          <a>
            <a href="controller?command=user_orders&id=${order.user.id}">${order.user.firstName} ${order.user.lastName}</a>
          </td>
          <td>${order.totalCost}</td>
          <td>${order.status}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</body>
</html>
