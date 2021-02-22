<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="profile">
    <div class="text-light row">
        <div class="col-sm-4">
            <c:if test="${not empty sessionScope.user.avatar_path }">
                <img class="avatar-big avatar-round m-2"
                     src="${pageContext.request.contextPath}/provide_image.do?file_name=${sessionScope.user.avatar_path}" >
            </c:if>

            <c:if test="${empty sessionScope.user.avatar_path}">
                <img class="avatar-big avatar-round m-2"
                     src="${pageContext.request.contextPath}${sessionScope.defaultAvatarPath}" >
            </c:if>
            <form action="avatar.upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" class="form-control-file"/>
                <input type="hidden" name="returnUrl" value="${pageContext.request.requestURL}" />
                <input type="submit" class="btn btn-primary m-1" value="<fmt:message key="upload"/>" />
            </form>
        </div>
        <div class="col-sm-8">
            <div class="section user-info row m-2 p-1">
                <div class="col-sm">
                    <h3 class="user-info__key"><fmt:message key="username"/></h3>
                    <c:if test="${not empty sessionScope.user.firstName}">
                        <h3 class="user-info__key"><fmt:message key="first_name"/></h3>
                    </c:if>
                    <c:if test="${not empty sessionScope.user.secondName}">
                        <h3 class="user-info__key"><fmt:message key="second_name"/></h3>
                    </c:if>
                    <h3 class="user-info__key"><fmt:message key="user_role"/></h3>

                    <h3 class="user-info__key"><fmt:message key="user_status"/></h3>

                    <c:if test="${sessionScope.user.emailConfirmed}">
                        <h3 class="user-info__key"><fmt:message key="email"/></h3>
                    </c:if>
                    <c:if test="${!sessionScope.user.emailConfirmed}">
                        <h3 class="user-info__key"><fmt:message key="email"/></h3>
                    </c:if>
                </div>
                <div class="col-sm">
                    <h3 class="user-info__value">${sessionScope.user.login}</h3>
                    <c:if test="${not empty sessionScope.user.firstName}">
                        <h3 class="user-info__value">${sessionScope.user.firstName}</h3>
                    </c:if>
                    <c:if test="${not empty sessionScope.user.secondName}">
                        <h3 class="user-info__value">${sessionScope.user.secondName}</h3>
                    </c:if>
                    <h3 class="user-info__value"><fmt:message key="${sessionScope.user.role.toString()}"/></h3>
                    <c:if test="${sessionScope.user.status.toString() == \"ACTIVE\"}">
                        <h3 class="user-info__value green"><fmt:message key="${sessionScope.user.status.toString()}"/></h3>
                    </c:if>
                    <c:if test="${sessionScope.user.status.toString() != \"ACTIVE\"}">
                        <h3 class="user-info__value red"><fmt:message key="${sessionScope.user.status.toString()}"/></h3>
                    </c:if>
                    <c:if test="${sessionScope.user.emailConfirmed}">
                        <h3 class="user-info__value green">${sessionScope.user.email}</h3>
                    </c:if>
                    <c:if test="${!sessionScope.user.emailConfirmed}">
                        <h3 class="user-info__value red"><fmt:message key="email.notConfirmed"/></h3>
                    </c:if>
                </div>
            </div>
            <a class="btn btn-primary edit-href m-1 l-1" href="${pageContext.request.contextPath}/profile_edit.do">
                <fmt:message key="edit_profile"/>
            </a>
        </div>
    </div>
    <form action="email_send.do" method="post" onsubmit=
            "return validateEmail(this.email.value);" class="section m-2 p-2 email-form row">

        <input class="form-control mr-2 col-sm-6" type="email" name="email" placeholder="<fmt:message key="email"/>">

        <button type="submit" class="btn btn-primary col-sm ">
            <fmt:message key="confirm_email"/>
        </button>
    </form>
</tags:general>
