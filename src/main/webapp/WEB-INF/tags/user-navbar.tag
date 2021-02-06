<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>

<c:if test="${sessionScope.user != null}">
    <a class="text-light mr-1" href="${pageContext.request.contextPath}/profile.do">
            ${sessionScope.user.login}(${sessionScope.user.role.toString()})
    </a>
    <a class="text-light" href="${pageContext.request.contextPath}/logout.do">
        <fmt:message key="logout" />
    </a>
</c:if>
<c:if test="${sessionScope.user == null}">
    <a href="${pageContext.request.contextPath}/login" class="text-light mr-1">
        <fmt:message key="login"/>
    </a>
    <a href="${pageContext.request.contextPath}/register" class="text-light mr-1">
        <fmt:message key="register"/>
    </a>
</c:if>