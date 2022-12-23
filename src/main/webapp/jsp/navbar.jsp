<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav">
    <li class="header">BookStore-Oleshko</li>
    <li><a href="controller?command=books">All Books</a></li>
    <li><a href="controller?command=users">All Users</a></li>
    <form action="controller" method="get">
        <label for="book">You can find book by id: </label>
        <input type="hidden" name="command" value="book"/>
        <input type="text" id="book" name="id" value=""/>
        <button type="submit"></button>
    </form>
    <form action="controller" method="get">
        <label for="user">You can find user by id: </label>
        <input type="hidden" name="command" value="user"/>
        <input type="text" id="user" name="id" value=""/>
        <button type="submit"></button>
    </form>
</ul>