<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="profile">
    <h1 class = "text-white"> PROFILE!!</h1>
    <div class="text-light">
        <c:if test="${not empty sessionScope.user.avatar_path }">
            <img class=`avatar-big avatar-round`
                 src="${pageContext.request.contextPath}${sessionScope.user.avatar_path}" >
        </c:if>
        <c:if test="${empty sessionScope.user.avatar_path}">
            <img class="avatar-big avatar-round"
                 src="${pageContext.request.contextPath}${sessionScope.defaultAvatarPath}" >
        </c:if>
            ${user.toString()} </br>
                <form action="avatar.upload" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" />
                    <input type="hidden" name="returnUrl" value="${pageContext.request.requestURL}" />
                    <input type="submit" value="upload" />
                </form>
    </div>
</tags:general>
