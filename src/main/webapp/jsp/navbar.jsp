<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav">
    <li class="header">BookStore-Oleshko</li>
    <li><a href="controller?command=books">All Books</a></li>
    <li><a href="controller?command=users">All Users</a></li>
    <form action="controller" method="get">
        <label for="book">Find book by author: </label>
        <input type="hidden" name="command" value="find_books" placeholder="author..."/>
        <input type="text" id="book" name="author" value=""/>
        <button type="submit"></button>
    </form>
    <form action="controller" method="get">
        <label for="user">You can find user by id: </label>
        <input type="hidden" name="command" value="user"/>
        <input type="text" id="user" name="id" value=""/>
        <button type="submit"></button>
    </form>
</ul>