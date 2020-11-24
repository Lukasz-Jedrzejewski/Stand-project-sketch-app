<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Realizacje w panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/header.jsp"%>
    <div id="realizations-admin">
        <c:forEach items="${realizations}" var="info">
            <div id="single-realization-admin">
                <c:if test="${info.important == false}">
                    <div id="single-image" style="border-color: red;">
                        <img src="<c:url value="/resources/images/realizations/${info.fileName}" />" alt="image" />
                    </div>
                </c:if>
                <c:if test="${info.important == true}">
                    <div id="single-image" style="border-color: green;">
                        <img src="<c:url value="/resources/images/realizations/${info.fileName}" />" alt="image" />
                    </div>
                </c:if>
                <div id="single-action">
                    <button><a href="/admin/set-important/${info.id}">na główną</a></button>
                    <button><a href="/admin/delete-realization/${info.id}">usuń</a></button>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="add-action">
        <a href="/admin/add-realizations">dodaj zdjęcia</a>
    </div>
</body>
</html>
