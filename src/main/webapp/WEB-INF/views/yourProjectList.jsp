<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your projects list</title>
</head>
<body>
<table>
    <tr>
        <th>Data utworzenia</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${userProjects}" var="up">
        <tr>
            <td>${up.created}</td>
            <td>
                <a href="/user/showDetails/${up.id}">szczegóły</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
