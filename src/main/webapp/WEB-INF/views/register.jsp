<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form modelAttribute="user" method="post" action="/user/register">
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
            Hasło<form:password path="password"/>
        </label>
        <form:errors path="password"/>
    </div>
    <div>
        <label>
            Powtórz hasło<input type="password" name="confirmPassword"/>
        </label>
    </div>
    <input id="b1" type="submit" value="Zarejestruj"/>
</form:form>
</body>
</html>
