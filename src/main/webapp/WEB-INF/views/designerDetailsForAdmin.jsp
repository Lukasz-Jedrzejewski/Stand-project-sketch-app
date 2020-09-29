<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły projektanta dla panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="navigation-bar.jsp" %>
<ul>
    <li>Imię</li>
    ${designer.name}
    <li>Nazwisko</li>
    ${designer.surname}
    <li>Zdjęcie</li>
    ${designer.photos}
    <br>
    <a href="/admin/add-designer-photo/${designer.id}">dodaj zdjęcie</a>
    <a href="/admin/delete-designer-photo/${designer.id}">usuń zdjęcie</a>
    <li>Opis</li>
    ${designer.description}
    <li>Akcje</li>
    <a href="/admin/edit-designer-info/${designer.id}">edytuj</a>
    <a href="/admin/delete-designer/${designer.id}">usuń</a>
</ul>
</body>
</html>
