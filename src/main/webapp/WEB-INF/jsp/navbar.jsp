<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav">
    <li class="header">BookStore-Oleshko</li>
    <li><a href="/">Home</a></li>
    <li><a href="/books/all/0">All Books</a></li>
    <li><a href="/users/all/0">All Users</a></li>
    <li><a href="/orders/all/0">All Orders</a></li>
    <div>
        <form action="/books/find" method="post">
            <label for="book">Find book by author: </label>
            <input type="text" id="book" name="author" placeholder="author..." value=""/>
            <input type="hidden" name="page" value="0">
            <button type="submit"></button>
        </form>
        <form action="/users/find" method="post">
            <label for="user">Find user by email: </label>
            <input type="email" id="user" name="email" placeholder="email..." value=""/>
            <button type="submit"></button>
        </form>
    </div>
</ul>