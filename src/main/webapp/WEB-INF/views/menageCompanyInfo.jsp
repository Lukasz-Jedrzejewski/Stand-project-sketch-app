<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zarządzanie informacjami o firmie</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<c:forEach items="${companyInfo}" var="info">
    <div>
        treść
        ${info.description}
        <a href="/admin/edit-company-info/${info.id}">edytuj</a>
    </div>
</c:forEach>
</body>
</html>
