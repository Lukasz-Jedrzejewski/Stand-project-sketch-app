<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Szczegóły projektanta dla panelu administratora</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="../home/navigation-bar.jsp" %>
<ul>
    <li>Imię</li>
        ${designer.name}
    <li>Nazwisko</li>
        ${designer.surname}
    <li>Zdjęcie</li>
        <div class="designer-list">
            <img src="<c:url value="/resources/images/${photography.fileName}" />" alt="image" />
        </div>
        <br>
    <c:if test="${photography.fileName != 'pexels-mohamed-abdelgaffar-771742.jpg'}">
        <a href="/admin/add-designer-photo/${designer.id}">dodaj zdjęcie</a>
        <a href="/admin/delete-designer-photo/${designer.id}">usuń zdjęcie</a>
    </c:if>
    <c:if test="${photography.fileName == 'pexels-mohamed-abdelgaffar-771742.jpg'}">
        <a href="/admin/add-designer-photo/${designer.id}">dodaj zdjęcie</a>
        </c:if>
    <li>Opis</li>
        ${designer.description}
    <li>Akcje</li>
        <a href="/admin/designer-info/${designer.id}">edytuj</a>
        <a href="/admin/delete-designer/${designer.id}">usuń</a>
</ul>
</body>
</html>
