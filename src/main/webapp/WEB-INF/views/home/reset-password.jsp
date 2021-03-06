<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Reset-password</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>
    <h2>Podaj swój adres email</h2>
    <form:form action="/reset-password" method="post" modelAttribute="emailModel">
        <div>
            <input type="email" name="email" placeholder="email">
        </div>
        <div>
            <button type="submit">Wyślij</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</div>
</body>
</html>
