<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz zarządzania projektantem</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="../home/header.jsp" %>
    <h1>Wgraj plik z rozszerzeniem <strong>.jpg</strong></h1>
    <form:form modelAttribute="companyInfo" method="post" action="/admin/add-logo" enctype="multipart/form-data">
        <input type="file" name="file" multiple required/>
        <button type="submit">Zapisz</button>
    </form:form>
</body>
</html>
