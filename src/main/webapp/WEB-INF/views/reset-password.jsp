<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Ronin
  Date: 23.09.2020
  Time: 09:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset-password</title>
</head>
<body>
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
