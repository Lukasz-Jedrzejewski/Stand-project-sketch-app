<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About company</title>
</head>
<body>
<c:forEach items="${companyInfo}" var="info">
    ${info.description}
</c:forEach>
</body>
</html>
