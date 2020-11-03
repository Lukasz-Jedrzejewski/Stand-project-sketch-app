<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header style="display: flex; flex-direction: row">
    <form action="/admin/branches">
        <input type="submit" value="zarządzaj listą branż"/>
    </form>
    <form action="/admin/events">
        <input type="submit" value="zarządzaj listą wydarzeń"/>
    </form>
    <form action="/admin/floorBoards">
        <input type="submit" value="zarządzaj rodzajami podłogi"/>
    </form>
    <form action="/admin/buildingTypes">
        <input type="submit" value="zarządzaj rodzajami zabudowy "/>
    </form>
    <form action="/admin/sketches">
        <input type="submit" value="lista wzorców"/>
    </form>
    <form action="/admin/adminPanel">
        <input type="submit" value="panel"/>
    </form>
    <form action="/admin/menage">
        <input type="submit" value="zarządzaj treścią"/>
    </form>
    <button type="button" name="back" onclick="history.back()">back</button>
</header>
