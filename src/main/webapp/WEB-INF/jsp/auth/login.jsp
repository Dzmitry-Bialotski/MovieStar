<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><fmt:message key="login"/></title>
</head>
<body>
<h1> Login </h1>
<form action="login.do" method="post">
    <input name="login" placeholder="login">
    <input name="password" type="password" placeholder="Пароль">
    <button type="submit"> login </button>
</form>
Login: ${login}
Password: ${password}
<c:forEach var="cook" items="${cookie}">
    <li>
        <p><c:out value="${cook.value.name}" /></p>
        <p><c:out value="${cook.value.value}" /></p>
    </li>
</c:forEach>
</body>
</html>
