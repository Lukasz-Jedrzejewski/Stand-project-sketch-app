<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Designers list</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="navigation-bar.jsp"%>
<c:forEach items="${photos}" var="photo">
<div id="designer-content-home">
    <div class="designer-list">
    <img src="<c:url value="/resources/images/${photo.fileName}" />" alt="image" />
    </div>
    <div id="designer-info">
        ${photo.designer.name} ${photo.designer.surname}
        </br>
        </br>
        <p>${photo.designer.description}</p>
    </div>
    </div>
</c:forEach>

</body>
</html>
