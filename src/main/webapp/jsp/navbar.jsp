<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav">
    <li class="header">BookStore-Oleshko</li>
    <li><a href="controller?command=books">All Books</a></li>
    <li><a href="controller?command=users">All Users</a></li>
    <li><a href="controller?command=orders">All Orders</a></li>
    <form action="controller" method="get">
        <label for="book">Find book by author: </label>
        <input type="hidden" name="command" value="find_books"/>
        <input type="text" id="book" name="author" placeholder="author..." value=""/>
        <button type="submit"></button>
    </form>
    <form action="controller" method="get">
        <label for="user">Find user by email: </label>
        <input type="hidden" name="command" value="find_user"/>
        <input type="text" id="user" name="email" placeholder="email..." value=""/>
        <button type="submit"></button>
    </form>
</ul>