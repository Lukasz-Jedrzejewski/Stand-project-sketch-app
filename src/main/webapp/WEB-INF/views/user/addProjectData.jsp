<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Project Data</title>
</head>
<body>
<button type="button" name="back" onclick="history.back()">back</button>
<form:form modelAttribute="project" method="post" action="/project/add">
    <form:hidden path="id" />
    <div>
        <label>
            Szerokość stoiska<form:input path="width"/>
        </label>
        <form:errors path="width"/>
    </div>
    <div>
        <label>
            Głębokość Stoiska<form:input path="depth"/>
        </label>
        <form:errors path="depth"/>
    </div>
    <div>
        <label>
            Rodzaj zabudowy<form:select path="typeOfBuilding" items="${typesOfBuilding}" itemLabel="name" itemValue="id"/>
        </label>
        <form:errors path="typeOfBuilding"/>
    </div>
    <div>
        <label>
            Piętro<form:checkbox path="withFloor"/>
        </label>
    </div>
    <div
    ><label>
        Podwieszenie<form:checkbox path="withHanger"/>
    </label>
    </div>
    <div>
        <label>
            Wysokość zabudowy<form:input path="standHeight"/>
        </label>
        <form:errors path="standHeight"/>
    </div>
    <div>
        <label>
            Zaplecze użytkowe<form:checkbox path="utilityRoom"/>
        </label>
    </div>
    <div>
        <label>
            VipRoom<form:checkbox path="vipRoom"/>
        </label>
    </div>
    <div>
        <label>
            Rodzaj podłogi<form:select path="floorBoard" items="${typesOfFloorBoard}" itemLabel="name" itemValue="id"/>
        </label>
        <form:errors path="floorBoard"/>
    </div>
    <div>
        <label>0 <form:radiobutton path="walls" value="0"/></label>
        <label>1 <form:radiobutton path="walls" value="1"/></label>
        <label>2 <form:radiobutton path="walls" value="2"/></label>
        <label>3 <form:radiobutton path="walls" value="3"/></label>
        <label>4 <form:radiobutton path="walls" value="4"/></label>
        <form:errors path="walls"/>
    </div>

    <input type="submit" value="Zatwierdź"/>
</form:form>
</body>
</html>
