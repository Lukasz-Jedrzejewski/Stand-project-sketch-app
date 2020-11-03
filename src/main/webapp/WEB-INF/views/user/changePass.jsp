<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change password</title>
</head>
<body>
<form:form modelAttribute="userPass" action="/user/changePass" method="post">
    <div>
        <label>
            Nowe hasło<form:password path="password"/>
        </label>
        <form:errors path="password"/>
    </div>
    <input type="submit" value="Zatwierdź"/>
</form:form>
</body>
</html>
