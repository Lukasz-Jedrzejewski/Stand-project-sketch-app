<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista projektantów w panelu administratora</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<c:forEach items="${designers}" var="info">
    <a href="/admin/designer-details/${info.id}" >
        <div class="designer-list">
            ${info.name} ${info.surname}
        </div>
    </a>
</c:forEach>
        <div style="clear: both;">
        <form action="/admin/designer-info">
            <input type="submit" value="dodaj projektanta">
        </form>
        </div>
</body>
</html>
