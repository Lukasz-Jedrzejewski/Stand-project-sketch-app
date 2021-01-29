<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Formularz zarządzania projektantem</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="../home/header.jsp" %>
    <form:form modelAttribute="files" method="post" action="/admin/add-designer-photo" enctype="multipart/form-data">
        <input type="file" name="file" multiple required/>
        <button type="submit">Zapisz</button>
    </form:form>
</body>
</html>
