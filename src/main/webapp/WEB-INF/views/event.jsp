<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event</title>
</head>
<body>
<button id="btn_form">Uzupełnij dane</button>
<button id ="btn_ready">Wybierz z listy</button>
<section class="form">
Uzupełnij dane
<form:form modelAttribute="currentEvent" method="post" action="/event/get">
    <div>
        <label>
            Nazwa wydarzenia<form:input path="name"/>
        </label>
        <form:errors path="name"/>
    </div>
    <div>
        <label>
            Miasto<form:input path="city"/>
        </label>
        <form:errors path="city"/>
    </div>
    <input id="b1" type="submit" value="Zatwierdź"/>
</form:form>
</section>
<section class="ready">
wybierz z listy:
    <div>
        <label>
                <select id="event_ready">
                <c:forEach items="${events}" var="event">
                    <option value="${event.id}">${event.name} | ${event.city}</option>
                </c:forEach>
                </select>

                <input id="b2" type="submit" value="Zatwierdź"/>
        </label>
    </div>
</section>
<script src="<c:url value="/resources/js/js.js"/>"></script>
</body>
</html>
