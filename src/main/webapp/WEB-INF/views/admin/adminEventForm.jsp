<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add branch form</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
<body>
<form:form modelAttribute="currentEvent" method="post" action="/admin/addEvent">
    <form:hidden path="id"/>
    <div>
        <label>
            nazwa wydarzenia <form:input path="name"/>
        </label>
        <form:errors path="name"/>
    </div>
    <div>
        <label>
            miasto <form:input path="city"/>
        </label>
        <form:errors path="city"/>
    </div>
    <input type="submit" value="Zatwierdź"/>
</form:form>
</body>
</html>
