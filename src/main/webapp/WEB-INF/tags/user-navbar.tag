<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ tag pageEncoding="UTF-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>

<c:if test="${sessionScope.user != null}">
    <c:if test="${sessionScope.user.role.toString() == 'ADMIN'}">
        <a class="text-light row" href="${pageContext.request.contextPath}/admin.do">
            <div class="navbar-avatar">
                <c:if test="${not empty sessionScope.user.avatar_path }">
                    <img class="avatar-small avatar-round mr-1"
                         src="${pageContext.request.contextPath}/provide_image.do?file_name=${sessionScope.user.avatar_path}" >
                </c:if>
                <c:if test="${empty sessionScope.user.avatar_path}">
                    <img class="avatar-small avatar-round mr-1"
                         src="${pageContext.request.contextPath}${sessionScope.defaultAvatarPath}" >
                </c:if>
            </div>
            <div class="navbar-username col-sm nav-link mr-1">
                    ${sessionScope.user.login}
            </div>
        </a>
    </c:if>
    <c:if test="${sessionScope.user.role.toString() != 'ADMIN'}">
        <a class="text-light row" href="${pageContext.request.contextPath}/profile.do">
            <div class="navbar-avatar">
                <c:if test="${not empty sessionScope.user.avatar_path }">
                    <img class="avatar-small avatar-round mr-1"
                         src="${pageContext.request.contextPath}/provide_image.do?file_name=${sessionScope.user.avatar_path}" >
                </c:if>
                <c:if test="${empty sessionScope.user.avatar_path}">
                    <img class="avatar-small avatar-round mr-1"
                         src="${pageContext.request.contextPath}${sessionScope.defaultAvatarPath}" >
                </c:if>
            </div>
            <div class="navbar-username col-sm nav-link mr-1">
                    ${sessionScope.user.login}
            </div>
        </a>
    </c:if>
    <a class="nav-link" href="${pageContext.request.contextPath}/logout.do">
        <i class="fas fa-sign-out-alt mr-1"></i><fmt:message key="logout" />
    </a>
</c:if>
<c:if test="${sessionScope.user == null}">
    <a href="${pageContext.request.contextPath}/login.do" class="nav-link mr-3">
        <i class="fas fa-sign-in-alt mr-1"></i><fmt:message key="login"/>
    </a>
    <a href="${pageContext.request.contextPath}/register.do" class="nav-link mr-1">
        <i class="fas fa-user-plus mr-1"></i><fmt:message key="register"/>
    </a>
</c:if>