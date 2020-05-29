<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Branch list</title>
</head>
<button type="button" name="back" onclick="history.back()">back</button>
<form action="/admin/adminPanel">
    <input type="submit" value="admin panel"/>
</form>
<body>
<table>
    <tr>
        <th>nazwa</th>
        <th>miasto</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${eventList}" var="event">
        <tr>
            <td>${event.name}</td>
            <td>${event.city}</td>
            <td>
                <a href="/admin/addEvent/${event.id}">edytuj</a>
                <a href="/admin/deleteEvent/${event.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/addEvent">dodaj</a>
</body>
</html>
