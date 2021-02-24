<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="edit_profile">
    <h2 class = "text-light pl-4 my-2"> <fmt:message key="edit_profile"/> </h2>
    <div class="text-light section m-1 p-1 email-form">
        <form action="profile_edit.do" method="post"onsubmit=
                "return validateName(this.first_name.value, this.second_name.value);">
            <input type="text" placeholder="<fmt:message key="FirstName" />" name="first_name" class="form-control m-1"
                    value="${sessionScope.user.firstName}">
            <input type="text" placeholder="<fmt:message key="SecondName" />" name="second_name" class="form-control m-1"
                    value="${sessionScope.user.secondName}">
            <button type="submit" class="btn btn-primary m-1"> <fmt:message key="edit_profile" /></button>
        </form>
        <div class="text-danger error">
            <c:if test="${not empty errorMessage}">
                ${errorMessage}
            </c:if>
        </div>
    </div>
</tags:general>

