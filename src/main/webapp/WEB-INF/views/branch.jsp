<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Branch</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<button id="btnBranch_form">Uzupełnij dane</button>
<button id ="btnBranch_ready">Wybierz z listy</button>
<section class="branchForm">
    Uzupełnij dane
    <form:form modelAttribute="branch" method="post" action="/branch/get">
        <div>
            <label>
                Nazwa branży<form:input path="name"/>
            </label>
            <form:errors path="name"/>
        </div>
        <input id="b1" type="submit" value="Zatwierdź"/>
    </form:form>
</section>
<section class="branchReady">
    wybierz z listy:
    <div>
        <label>
            <select id="branch_ready">
                <c:forEach items="${branches}" var="branch">
                    <option value="${branch.id}">${branch.name}</option>
                </c:forEach>
            </select>

            <input id="b2" type="submit" value="Zatwierdź"/>
        </label>
    </div>
</section>
<script src="<c:url value="/resources/js/branch.js"/>"></script>
</body>
</html>