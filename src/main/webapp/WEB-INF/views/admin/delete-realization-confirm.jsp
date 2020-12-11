<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Potwierdzenie usunięcia zdjęcia realizacji</title>
</head>
<body>
<%@include file="headerAdmin.jsp"%>
<%@include file="../home/header.jsp"%>
    <div id="realizations-admin">
        <form:form action="/admin/delete-realization" method="post" modelAttribute="realization">
            <form:hidden path="id"/>
            <form:hidden path="fileName"/>
            <div id="single-realization-admin">
                <div id="single-image">
                    <img src="<c:url value="/resources/images/realizations/${realization.fileName}" />" alt="image" />
                </div>
            </div>
            <h1>Czy na pewno chcesz usunąć to zdjęcie?</h1>
            <input type="submit" value="zatwierdź"/>
            <button><a href="/admin/realizations"/>odrzuć</a></button>
        </form:form>
    </div>
</body>
</html>
