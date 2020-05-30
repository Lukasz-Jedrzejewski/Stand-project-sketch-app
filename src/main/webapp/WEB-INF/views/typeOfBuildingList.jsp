<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Type of building list</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
<body>
<table>
    <tr>
        <th>nazwa</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${typeOfBuildingList}" var="type">
        <tr>
            <td>${type.name}</td>
            <td>
                <a href="/admin/addTypeOfBuilding/${type.id}">edytuj</a>
                <a href="/admin/deleteTypeOfBuilding/${type.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/addTypeOfBuilding">dodaj</a>
</body>
</html>
