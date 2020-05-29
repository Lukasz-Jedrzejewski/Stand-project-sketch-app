<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add branch form</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form action="/admin/adminPanel">
    <input type="submit" value="admin panel"/>
</form>
<form:form modelAttribute="event" method="post" action="/admin/addEvent">
    <form:hidden path="id"/>
    <div>
        <label>
            nazwa branży <form:input path="name"/>
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
