<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event</title>
</head>
<body>
Uzupełnij dane
<form:form modelAttribute="currrentEvent" method="post" action="/event/get">
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
    <input type="submit" value="Zatwierdź"/>
</form:form>

lub wybierz z listy:
    <div>
        <label>
                <select>
                <c:forEach items="${events}" var="event">
                    <option>${event.name} | ${event.city}</option>
                </c:forEach>
                <input type="submit" value="Zatwierdź"/>
            </select>
        </label>
    </div>

</body>
</html>
