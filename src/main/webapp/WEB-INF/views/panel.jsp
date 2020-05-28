<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel</title>
</head>
<body>
AAAAAAAAAAAAAAAAAAAAA
<nav>
    bbbb
            <sec:authorize access="isAuthenticated()">
                <p>Zalogowany jako: <sec:authentication property="companyName"/></p>
                <p>Posiada role: <sec:authentication property="authorities"/></p>

            </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <form action="<c:url value="/logout"/>" method="post">
            <input type="submit" value="Wyloguj">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </sec:authorize>
</nav>
<a href="/event/get">Uzupełnij dane do projektu</a>
</body>
</html>