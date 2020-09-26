<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company info form</title>
</head>
<body>
<%@include file="navigation-bar.jsp"%>
<form:form action="/edit-company-info" method="post" modelAttribute="companyInfo">
    <div>
        <form:hidden path="id"/>
        <label>
            <form:textarea path="description" rows="30" cssStyle="width: 1000px"/>
        </label>
    </div>
    <input type="submit" value="zatwierdź">
</form:form>
</body>
</html>
