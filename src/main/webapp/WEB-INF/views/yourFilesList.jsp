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
            <th>nazwa</th>
            <th>akcje</th>
        </tr>
            <c:forEach items="${files}" var="file">
                <tr>
                    <td>${file.id}</td>
                    <td>${file.fileName}</td>
                    <td>
                        <a href="/file/download/${file.id}">pobierz</a>
                        <a href="/file/display/${file.id}">wyświetl</a>
                    </td>
                </tr>
            </c:forEach>
    </table>
</div>
</body>
</html>
