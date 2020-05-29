<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Floorboard list</title>
</head>
<button type="button" name="back" onclick="history.back()">back</button>
<form action="/admin/adminPanel">
    <input type="submit" value="admin panel"/>
</form>
<body>
<table>
    <tr>
        <th>nazwa</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${floorBoardList}" var="fb">
        <tr>
            <td>${fb.name}</td>
            <td>
                <a href="/admin/addFloorBoard/${fb.id}">edytuj</a>
                <a href="/admin/deleteFloorBoard/${fb.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/addFloorBoard">dodaj</a>
</body>
</html>
