<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista projektantów w panelu administratora</title>
</head>
<body>
<%@include file="navigation-bar.jsp"%>
<c:forEach items="${designers}" var="info">
    <div>
        treść
            ${info.name}
            ${info.surname}
            ${info.description}
            ${info.photography}
        <a href="/admin/edit-designer-info/${info.id}">edytuj</a>
    </div>
</c:forEach>
</body>
</html>
