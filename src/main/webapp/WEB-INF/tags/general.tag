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
    <jsp:include page="/WEB-INF/jsp/shared/style_import.jsp"/>
</head>
<body class="login_body">
<jsp:include page="/WEB-INF/jsp/shared/header.jsp"/>
<div class="container">
    <jsp:doBody/>
</div>
<jsp:include page="/WEB-INF/jsp/shared/scripts_import.jsp"/>
</body>
</html>
