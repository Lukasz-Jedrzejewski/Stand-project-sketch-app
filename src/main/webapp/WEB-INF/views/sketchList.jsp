<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sketch list</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
<div style="display: flex; flex-direction: row">
    <form action="/admin/getUserMail">
        <input type="submit" value="szukaj projektów użytkownika"/>
    </form>
    <form action="/admin/sorted">
        <input type="submit" value="sortuj od najnowszych"/>
    </form>
    <form action="/admin/showFiles">
        <input type="submit" value="Wyświetl wszystkie pliki"/>
    </form>
</div>
<body>
<table>
    <tr>
        <th>Data utworzenia</th>
        <th>Utworzone przez</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${sketches}" var="sk">
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
