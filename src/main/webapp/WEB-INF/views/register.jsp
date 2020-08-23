<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form name="form" modelAttribute="user" method="post" action="/user/register">
    <div>
        <label>
            Nazwa firmy<form:input path="companyName"/>
        </label>
        <form:errors path="companyName"/>
    </div>
    <div>
        <label>
            Mail firmy<form:input path="companyMail"/>
        </label>
        <form:errors path="companyMail"/>
    </div>
    <div>
        <label>
            Hasło<form:password path="password" id="pass"/>
        </label>
        <form:errors path="password"/>
    </div>
    <div>
        <label id="confirm">
            Powtórz hasło<input type="password" name="confirmPassword" id="confirmation"/>
        </label>
    </div>
    <input id="b1" type="submit" value="Zarejestruj"/>
</form:form>
<script src="<c:url value="/resources/js/register.js"/>"></script>
</body>
</html>
