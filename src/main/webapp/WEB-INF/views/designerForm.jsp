<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz zarządzania projektantem</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="navigation-bar.jsp" %>
<form:form action="/admin/edit-designer" method="post" modelAttribute="designer">
    <form:hidden path="id"/>
    <ul>
        <label>Imię</label>
        <li><form:input path="name"/></li>
        <label>Nazwisko</label>
        <li><form:input path="surname"/></li>
<%--        <label>Zdjęcie</label>--%>
<%--        <li><input type="file" name="file" /></li>--%>
        <label>Opis</label>
        <li><form:input path="description"/></li>
    </ul>
    <input type="submit" value="Zatwierdź"/>
</form:form>
</body>
</html>
