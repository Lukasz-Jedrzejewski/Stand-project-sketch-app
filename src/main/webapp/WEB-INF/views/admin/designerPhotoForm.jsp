<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formularz zarządzania projektantem</title>
</head>
<body>
<%@include file="headerAdmin.jsp" %>
<%@include file="../home/navigation-bar.jsp" %>
    <form:form modelAttribute="files" method="post" action="/admin/add-designer-photo" enctype="multipart/form-data">
        <input type="file" name="files" multiple required/>
        <button type="submit">Zapisz</button>
    </form:form>
</body>
</html>
