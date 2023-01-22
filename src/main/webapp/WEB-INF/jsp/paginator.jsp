<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination">
    <a href="controller?command=${requestScope.command}&page=${requestScope.page > 1 ? requestScope.page - 1 : 0}">Previous page</a>
    <a href="controller?command=${requestScope.command}&page=${requestScope.page}" class="active">${requestScope.page + 1}</a>
    <a href="controller?command=${requestScope.command}&page=${requestScope.page < requestScope.totalPages ? requestScope.page + 1 : requestScope.totalPages}">Next page</a>
</div>