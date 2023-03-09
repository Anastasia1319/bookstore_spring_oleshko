<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination">
    <a href="${page > 1 ? page - 1 : 0}">Previous page</a>
    <a href="${page}" class="active">${page + 1}</a>
    <a href="${page < totalPages ? page + 1 : totalPages}">Next page</a>
</div>