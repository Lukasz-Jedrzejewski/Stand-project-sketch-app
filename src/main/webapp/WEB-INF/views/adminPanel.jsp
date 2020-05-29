<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>
<header>Panel administratora</header>
<a href="/admin/branches">Zarządzaj listą branż</a>
<nav>
    <sec:authorize access="isAuthenticated()">
        <p>Zalogowany jako: ${user.companyMail}</p>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <form action="<c:url value="/logout"/>" method="post">
            <input type="submit" value="Wyloguj">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize>
</nav>
</body>
</html>
