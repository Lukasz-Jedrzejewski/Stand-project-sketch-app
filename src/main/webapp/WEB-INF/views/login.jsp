
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <button type="button" name="back" onclick="history.back()">back</button>
    <form method="post">
        <div><label> User Mail : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <div><input type="submit" value="Zaloguj"/></div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
<div>
    <a href="/reset-password">Zapomniałem hasło</a>
</div>
</body>
</html> 