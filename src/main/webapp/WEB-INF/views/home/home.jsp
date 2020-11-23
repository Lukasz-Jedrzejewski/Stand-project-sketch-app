<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Homepage</title>
</head>
<body>
<header>
    <div id="head">
        <div id="logo">
            <img src="/resources/images/${logo.logoName}"/>
        </div>
        <div id="actions">
        <ul>
            <li><a href="/user/about">logowanie</a></li>
            <li><a href="/register">Rejestracja</a></li>
        </ul>
        </div>
        <div style="clear: both;"></div>
    </div>
</header>
<%@include file="navigation-bar.jsp"%>
    <!--
    other sections here!
    -->
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
