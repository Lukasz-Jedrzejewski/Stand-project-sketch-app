<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add floorboard form</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form action="/admin/adminPanel">
    <input type="submit" value="admin panel"/>
</form>
<form:form modelAttribute="floorBoard" method="post" action="/admin/addFloorBoard">
    <form:hidden path="id"/>
    <div>
        <label>
            rodzaj podłogi: <form:input path="name"/>
        </label>
        <form:errors path="name"/>
    </div>
    <input type="submit" value="Zatwierdź"/>
</form:form>
</body>
</html>
