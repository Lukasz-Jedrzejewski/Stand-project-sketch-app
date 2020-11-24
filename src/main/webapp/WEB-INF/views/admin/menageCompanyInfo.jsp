<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Zarządzanie informacjami o firmie</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/header.jsp"%>
<c:forEach items="${companyInfo}" var="info">
    <div>
        <ul>
            <li><img src="/resources/images/logo/company-logo.jpeg"/></li>
                <a href="/admin/add-logo/${info.id}">zmień logo</a>
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
                ${info.buildingNumber}
            </li>
            <li>
                ${info.apartmentNumber}
            </li>
            <li>
                ${info.zipCode}
            </li>
            <li>
                ${info.description}
            </li>
            <li>
                <a href="/admin/edit-company-info/${info.id}">edytuj</a>
            </li>
        </ul>
    </div>
</c:forEach>
</body>
</html>
