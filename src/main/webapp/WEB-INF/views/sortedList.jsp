<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sorted list</title>
</head>
<%@include file="headerAdmin.jsp"%>
<body>
<table>
    <tr>
        <th>Data utworzenia</th>
        <th>Utworzone przez</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${sorted}" var="sk">
        <tr>
            <td>${sk.created}</td>
            <td>${sk.companyName}</td>
            <td>
                <a href="/admin/showDetails/${sk.id}">szczegóły</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
