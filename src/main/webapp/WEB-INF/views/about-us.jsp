<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About company</title>
</head>
<body>
<%@include file="navigation-bar.jsp"%>
<div>
    <c:forEach items="${companyInfo}" var="info">
        ${info.description}
    </c:forEach>
</div>
</body>
</html>
