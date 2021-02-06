<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="true" %>

<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><fmt:message key="${title}" /> - MovieStar</title>
    <jsp:include page="import_css"/>
</head>
<body class="login_body">
<jsp:include page="navigation"/>
<div class="container">
    <jsp:doBody/>
</div>
<jsp:include page="import_js"/>
</body>
</html>
