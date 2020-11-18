<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Realizacje w panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/navigation-bar.jsp"%>
<c:forEach items="${realizations}" var="info">
    <div>
        <img src="<c:url value="/resources/images/realizations/${info.fileName}" />" alt="image" />
    </div>
</c:forEach>
    <a href="/admin/add-realizations">dodaj zdjęcia</a>
</body>
</html>
