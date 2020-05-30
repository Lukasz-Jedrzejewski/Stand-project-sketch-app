<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Branch list</title>
</head>
<h4>Panel administratora</h4>
<%@include file="headerAdmin.jsp" %>
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
                <a href="/admin/addBranch/${branch.id}">edytuj</a>
                <a href="/admin/deleteBranch/${branch.id}">usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/addBranch">dodaj</a>
</body>
</html>
