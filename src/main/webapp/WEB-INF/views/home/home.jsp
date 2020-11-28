<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<%@include file="header.jsp"%>
    <!--
    other sections here!
    -->
    <div id="designers-home">
    <h1>Oddaj swoje sprawy w nasze ręce!</h1>
        <div class="border"></div>
        <c:forEach items="${designers}" var="single" varStatus="status">
            <div id="single-designer">
                <img src="<c:url value="/resources/images/${single.photoName}" />" alt="image" />
                <p>${single.name} ${single.surname}</p>
            </div>
        </c:forEach>
    </div>
    <div id="realizations-home">
        <h2>Wybrane realizacje</h2>
        <div class="border"></div>
        <div id="realization-display">
            <img id="rel" src=""/>
            <script>
            var values = new Array();
            <c:forEach items="${realizations}" var="single">
                values.push("${single.fileName}")
            </c:forEach>
            var img = document.getElementById('rel');
            const timer = ms => new Promise(res => setTimeout(res, ms));
            async function load () {
                var i = 0;
                for (var i = i; i < values.length; i++) {
                img.src = "/resources/images/realizations/"+values[i];
                await timer(4000);
                if (i === values.length-1) {
                    i = 0;
                }
                }
            }
            load();
            </script>
        </div>
        <button id="contact-submit"><a href="/realisations">więcej realizacji</a></button>
    </div>
    <div id="contact">
        <div id="contact-note">
            <h1>Chcesz dowiedzieć się więcej?</h1>
            <div class="border"></div>
            <p><strong>Napisz do nas, oddzwonimy!</strong><p>
        </div>
        <div id="contact-form">
            <form:form action="/send-contact-message" method="post" modelAttribute="message">
                <div class="form-field">
                    <label>Imię i nazwisko/Nazwa firmy</label>
                    <input type="text" name="name"/>
                <div>
                <div class="form-field">
                    <label>Email</label>
                    <input type="text" name="email"/>
                <div>
                <div class="form-field">
                    <label>Numer telefonu</label>
                    <input type="text" name="phoneNumber"/>
                <div>
                <div class="form-field">
                    <label>O co chcesz zapytać?</label>
                    <textarea name="message"></textarea>
                <div>
                <button id="contact-submit" type="submit">Wyślij</button>
            </form:form>
        </div>
    </div>
</body>
</html>
