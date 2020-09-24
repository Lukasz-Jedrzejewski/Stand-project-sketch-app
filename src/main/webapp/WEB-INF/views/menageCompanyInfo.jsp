<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zarządzanie informacjami o firmie</title>
</head>
<body>
<c:forEach items="${companyInfo}" var="info">
    <div>
        ${info.description}
        <a href="/admin/add-company-info/${info.id}"/>
        <a href="/admin/edit-company-info/${info.id}"/>
    </div>
</c:forEach>
</body>
</html>
