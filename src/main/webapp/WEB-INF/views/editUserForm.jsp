<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user form</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form modelAttribute="user" method="post" action="/user/edit">
    <form:hidden path="id" />
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
    <input id="b1" type="submit" value="Zapisz"/>
</form:form>
</body>
</html>
