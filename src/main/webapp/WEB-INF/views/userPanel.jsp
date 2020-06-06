<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel</title>
</head>
<body>
<header>Panel użytkownika</header>
<form action="/user/edit/${user.id}">
    <input type="submit" value="Edytuj swoje konto">
</form>
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
<form action="/event/get">
    <input type="submit" value="Uzupełnij dane do projektu">
</form>
<form action="/user/mySketches">
    <input type="submit" value="Twoje szkice">
</form>
</body>
</html>