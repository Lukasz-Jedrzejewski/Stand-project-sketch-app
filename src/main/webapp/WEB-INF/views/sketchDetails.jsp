<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sketch Details</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
<body>
<table>
    <tr>
        <th>Szerokość stoiska</th>
        <th>Głębokość stoiska</th>
        <th>Rodzaj zabudowy</th>
        <th>Piętro</th>
        <th>Podwieszenie</th>
        <th>Wysokość ścian</th>
        <th>Zaplecze</th>
        <th>Vip Room</th>
        <th>Rodzaj podłogi</th>
        <th>Liczba ścian</th>
        <th>Branża</th>
        <th>Przesłane przez:</th>
        <th>Kontakt</th>
        <th>Dnia</th>
        <th>Akcje</th>
    </tr>
        <tr>
            <td>${sketch.width}</td>
            <td>${sketch.depth}</td>
            <td>${sketch.typeOfBuilding.name}</td>
            <td>${sketch.withFloor}</td>
            <td>${sketch.withHanger}</td>
            <td>${sketch.standHeight}</td>
            <td>${sketch.utilityRoom}</td>
            <td>${sketch.vipRoom}</td>
            <td>${sketch.floorBoard.name}</td>
            <td>${sketch.walls}</td>
            <td>${sketch.branch.name}</td>
            <td>${sketch.companyName}</td>
            <td>${sketch.companyMail}</td>
            <td>${sketch.created}</td>
            <td>
                <a href="/admin/addProposition/">dodaj wizualizację</a>
            </td>
        </tr>
</table>
</body>
</html>
