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
<form:form modelAttribute="branch" method="post" action="/admin/addBranch">
    <form:hidden path="id"/>
    <div>
        <label>
            nazwa branży <form:input path="name"/>
        </label>
        <form:errors path="name"/>
    </div>
</form:form>
</body>
</html>
