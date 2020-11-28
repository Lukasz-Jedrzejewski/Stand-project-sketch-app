<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Designers list</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="header.jsp"%>
<c:forEach items="${designer}" var="info">
<div id="designer-content-home">
    <div class="designer-list">
        <img src="<c:url value="/resources/images/${info.photoName}" />" alt="image" />
    </div>
    <div id="designer-info">
        ${info.name} ${info.surname}
        </br>
        </br>
        <p>${info.description}</p>
    </div>
    </div>
</c:forEach>

</body>
</html>
