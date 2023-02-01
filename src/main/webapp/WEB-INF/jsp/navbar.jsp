<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav">
    <li class="header">BookStore-Oleshko</li>
    <li><a href="/">Home</a></li>
    <li><a href="/books/page=0">All Books</a></li>
    <li><a href="/users/page=0">All Users</a></li>
    <li><a href="/orders/page=0">All Orders</a></li>
    <div>
        <form action="/books/find/${author}" method="get">
            <label for="book">Find book by author: </label>
            <input type="text" id="book" name="author" placeholder="author..." value=""/>
            <input type="hidden" value="/page=0"/>
            <button type="submit"></button>
        </form>
        <form action="/users/find/${email}" method="get">
            <label for="user">Find user by email: </label>
            <input type="hidden" name="command" value="find_user"/>
            <input type="text" id="user" name="email" placeholder="email..." value=""/>
            <input type="hidden" value="/page=0"/>
            <button type="submit"></button>
        </form>
    </div>
</ul>