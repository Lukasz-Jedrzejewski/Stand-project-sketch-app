<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user form</title>
</head>
<body>
<%@include file="../home/header.jsp"%>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form modelAttribute="user" method="post" action="/user/edit">
    <form:hidden path="id" />
    <form:hidden path="companyMail" />
    <form:hidden path="password" />
    <form:hidden path="enabled" />
    <form:hidden path="admin" />
    <form:hidden path="roles" />
    <div>
        <label>
            Nazwa firmy<form:input path="companyName"/>
        </label>
        <form:errors path="companyName"/>
    </div>
    <div>
        <label>
            Mail firmy<input name="email"/>
        </label>
    </div>
    <input id="b1" type="submit" value="Zapisz"/>
</form:form>
</body>
</html>
