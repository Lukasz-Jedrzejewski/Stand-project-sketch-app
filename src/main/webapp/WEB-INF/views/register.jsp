<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form modelAttribute="user" method="post" action="/user/register">
    <div>
        <label id="nameLabel">
            Nazwa firmy<form:input path="companyName" id="companyName"/>
        </label>
        <form:errors path="companyName"/>
    </div>
    <div>
        <label id="mailLabel">
            Mail firmy<form:input path="companyMail" id="companyMail"/>
        </label>
        <form:errors path="companyMail"/>
    </div>
    <div>
        <label id="passLabel">
            Hasło<form:password path="password" id="pass"
                                pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"/>
        </label>
        <form:errors path="password"/>
    </div>
    <div>
        <label id="confirmLabel">
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
    <input id="b1" type="submit" value="Zarejestruj"/>
</form:form>
<script src="<c:url value="/resources/js/register.js"/>"></script>
</body>
</html>
