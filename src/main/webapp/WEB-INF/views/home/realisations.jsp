<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>About realisations</title>
</head>
<body>
<%@include file="header.jsp"%>
    <div id="realizations-admin">
        <c:forEach items="${realizations}" var="info">
            <div id="single-realization-admin">
                    <div id="single-image">
                        <img src="<c:url value="/resources/images/realizations/${info.fileName}" />" alt="image" />
                    </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
