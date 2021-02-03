<%@ page import="by.belotskiy.movie_star.controller.command.CommandResult" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title> Home - MovieStar </title>
    <jsp:include page="import_css"/>
</head>

<body>
<jsp:include page="navigation"/>
<h1> Home </h1>
<a href="${pageContext.request.contextPath}/login"> login</a>
Login: ${login}
Password: ${password}

<jsp:include page="import_js"/>
</body>
</html>