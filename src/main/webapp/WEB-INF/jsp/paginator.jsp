<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination">
    <a href="${command}&page=${page > 1 ? page - 1 : 0}">Previous page</a>
    <a href="${command}&page=${page}" class="active">${page + 1}</a>
    <a href="${command}&page=${page < totalPages ? page + 1 : totalPages}">Next page</a>
</div>