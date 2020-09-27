<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Szczegóły projektanta dla panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="navigation-bar.jsp"%>
    <ul>
        <li>Imię</li>
        <span>${designer.name}</span>
        <li>Nazwisko</li>
        <span>${designer.surname}</span>
        <li>Zdjęcie</li>
        <span>${designer.photos}</span>
        <li>Opis</li>
        <span>${designer.description}</span>
        <li>Akcje</li>
        <span>
            <a href="/admin/edit-designer/${designer.id}">edytuj</a>
            <a href="/admin/delete-designer/${designer.id}">usuń</a>
        </span>
    </ul>
</body>
</html>
