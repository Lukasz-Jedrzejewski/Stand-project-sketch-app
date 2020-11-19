<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Realizacje w panelu administratora</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/navigation-bar.jsp"%>
    <div id="realizations-admin">
        <c:forEach items="${realizations}" var="info">
            <div id="single-realization-admin">
                <div id="single-image">
                    <img src="<c:url value="/resources/images/realizations/${info.fileName}" />" alt="image" />
                </div>
                <div id="single-action">
                    <button><a href="/">na główną</a></button>
                    <button><a href="/">usuń</a></button>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="add-action">
        <a href="/admin/add-realizations">dodaj zdjęcia</a>
    </div>
</body>
</html>
