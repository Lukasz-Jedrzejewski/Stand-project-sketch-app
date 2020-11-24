<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About company</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
          integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
          crossorigin=""/>

    <script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
            integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
            crossorigin=""></script>
</head>
<body>
<%@include file="header.jsp"%>
<div>
    <c:forEach items="${companyInfo}" var="info">
        <ul>
            <li>
                ${info.country}
            </li>
            <li>
                ${info.city}
            </li>
            <li>
                ${info.street}
            </li>
            <li>
                ${info.buildingNumber}
            </li>
            <li>
                ${info.apartmentNumber}
            </li>
            <li>
                ${info.zipCode}
            </li>
            <li>
                ${info.description}
            </li>
        </ul>
    </c:forEach>

<div id="mapid"></div>

            <script>
                var map = L.map('mapid').setView([${coordinates.lat}, ${coordinates.lng}], 18);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                }).addTo(map);
                L.marker([${coordinates.lat}, ${coordinates.lng}]).addTo(map)
                    .bindPopup("Tu jesteśmy")
                    .openPopup();
                    console.log(${coordinates.lat});
                    console.log(${coordinates.lng});
            </script>

        </div>
</body>
</html>
