<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company info form</title>
</head>
<body>
<%@include file="navigation-bar.jsp"%>
<form:form action="/admin/edit-company-info" method="post" modelAttribute="companyInfo">
    <form:hidden path="id"/>
    <div>
        <label>
            <form:input path="country" placeholder="kraj"/>
        </label>
    </div>
    <div>
        <label>
             <form:input path="city" placeholder="miasto"/>
        </label>
    </div>
    <div>
        <label>
             <form:input path="street" placeholder="ulica"/>
        </label>
    </div>
    <div>
        <label>
             <form:input path="zipCode" placeholder="kod pocztowy"/>
        </label>
    </div>
    <div>
        <label>
            <form:textarea path="description" rows="30" cssStyle="width: 1000px"/>
        </label>
    </div>
    <input type="submit" value="zatwierdź">
</form:form>
</body>
</html>
