<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File form</title>
</head>
<body>
<div>
    <form:form modelAttribute="files" method="post" action="/admin/addProposition" enctype="multipart/form-data">
        <input type="file" name="files" multiple required/>
        <button type="submit">Zapisz</button>
    </form:form>
</div>
</body>
</html>
