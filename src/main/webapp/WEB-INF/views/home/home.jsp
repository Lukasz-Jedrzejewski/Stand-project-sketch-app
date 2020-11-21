<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        </div>
    </div>
</body>
</html>
