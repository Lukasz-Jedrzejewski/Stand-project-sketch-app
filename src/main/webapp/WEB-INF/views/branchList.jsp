<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Branch list</title>
</head>
<body>
<table>
    <tr>
        <th>nazwa</th>
        <th>Akcje</th>
    </tr>
    <c:forEach items="${branchList}" var="branch">
        <tr>
            <td>${branch.name}</td>
            <td>
                <a href="/admin/edit/${branch.id}">edytuj</a>
                <a href="/admin/delete/${branch.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
