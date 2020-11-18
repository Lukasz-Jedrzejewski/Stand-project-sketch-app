<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zarządzanie informacjami o firmie</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/navigation-bar.jsp"%>
<c:forEach items="${realisations}" var="info">
    <div>
        <img src="<c:url value="/resources/images/realisations/${info.fileName}" />" alt="image" />
    </div>
</c:forEach>
</body>
</html>
