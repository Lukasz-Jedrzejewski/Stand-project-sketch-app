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
        <ul>
            <li>
                ${info.country}
            </li>
            <li>
                ${info.city}
            </li>
            <li>
                ${info.street}
            </li>
            <li>
                ${info.zipCode}
            </li>
            <li>
                ${info.description}
            </li>
        </ul>
    </c:forEach>
</div>
</body>
</html>
