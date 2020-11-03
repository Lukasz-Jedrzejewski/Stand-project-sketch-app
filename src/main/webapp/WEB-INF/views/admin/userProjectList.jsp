<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User projects list</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
<body>
<table>
    <tr>
        <th>Data utworzenia</th>
        <th>Utworzone przez</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${userProjects}" var="up">
        <tr>
            <td>${up.created}</td>
            <td>${up.companyName}</td>
            <td>
                <a href="/admin/showDetails/${up.id}">szczegóły</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
