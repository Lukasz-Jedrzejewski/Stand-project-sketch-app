<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change password</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<%@include file="../home/header.jsp"%>
<form:form modelAttribute="user" action="/user/changePass" method="post">
    <form:hidden path="id" />
    <form:hidden path="companyName" />
    <form:hidden path="companyMail" />
    <form:hidden path="enabled" />
    <form:hidden path="admin" />
    <form:hidden path="roles" />
    <div>
        <label>
            Nowe hasło<form:password path="password" id="pass"
                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"/>
        </label>
        <form:errors path="password"/>
    </div>
    <div>
        <label>
            Powtórz hasło<input type="password" name="confirmPassword" id="confirmation"
                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"/>
        </label>
    </div>

    <div id="message">
        <h3>Hasło musi składać się z:</h3>
        <p id="letter" class="invalid"><b>małych liter</b></p>
        <p id="capital" class="invalid"><b>dużych liter</b></p>
        <p id="number" class="invalid"><b>cyfr</b></p>
        <p id="length" class="invalid">Minimum <b>8 znaków</b></p>
        <p id="mark" class="invalid"><b>znaków specjalnych @$!%*?&</b></p>
    </div>
    <span id="spanMsg">Pole wymagane</span>

    <button type="submit" value="submit" id="sub">Zatwierdź</button>
</form:form>
<script src="<c:url value="/resources/js/register.js"/>"></script>
</body>
</html>
