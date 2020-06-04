<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files list</title>
</head>
<body>

<div>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
        </tr>
            <c:forEach items="${files}" var="file">
                <tr>
                    <td>${file.id}</td>
                    <td>${file.fileName}</td>
                </tr>
            </c:forEach>
    </table>
</div>
</body>
</html>
