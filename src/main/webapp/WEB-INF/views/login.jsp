<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form:form method="post" action="/user/login" modelAttribute="user">
    <div>
        <label>
            Company Name : <form:input path="companyName"/>
        </label>
        <form:errors path="companyName"/>
    </div>
    <div>
        <label>
            Company mail : <form:input path="companyMail"/>
        </label>
        <form:errors path="companyMail"/>
    </div>
    <div>
        <label>
            Password: <form:password path="password"/>
        </label>
        <form:errors path="password"/>
    </div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form:form>

</body>
</html>