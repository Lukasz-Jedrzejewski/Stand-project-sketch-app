<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Designers list</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<c:forEach items="${photos}" var="photo">
    <div class="designer-photo-home">
    <img src="<c:url value="/resources/images/${photo.fileName}" />" alt="image" />
        <div style="float: right">
        <ul>
            <li>${photo.designer.name}</li>
            <li>${photo.designer.surname}</li>
            <li>${photo.designer.description}</li>
        </ul>
        </div>
    </div>
</c:forEach>
</body>
</html>
