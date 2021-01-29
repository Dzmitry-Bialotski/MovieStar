<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${currentLocale}}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><fmt:message key="register"/> - MovieStar</title>
    <jsp:include page="import_css"/>
</head>
<body>
<jsp:include page="navigation"/>
<h1> Register </h1>
<form action="register.do" method="post">
    <input name="login" placeholder=<fmt:message key="username"/>>
    <input name="password" type="password" placeholder=<fmt:message key="password"/>>
    <input name="passwordConfirm" type="password" placeholder=<fmt:message key="passwordConfirm"/>>
    <button type="submit"> login </button>
</form>
Login: ${login}
Password: ${password}
<jsp:include page="import_js"/>
</body>
</html>
