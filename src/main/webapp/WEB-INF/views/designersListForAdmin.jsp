<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista projektantów w panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<h1>bla bla bla</h1>
<c:forEach items="${designers}" var="info">
    <div>
        <table>
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>akcje</th>
            </tr>
            <tr>
                <td>${info.name}</td>
                <td>${info.surname}</td>
                <td><a href="/admin/designer-details/${info.id}">więcej</a></td>
            </tr>
        </table>
    </div>
</c:forEach>
        <form action="/admin/designer-info">
            <input type="submit" value="dodaj projektanta">
        </form>
</body>
</html>
