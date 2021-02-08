<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="edit_profile">
    <h1 class = "text-white"> PROFILE!!</h1>
    <div class="text-light">
        <form action="profile_edit.do" method="post">
            <input type="text" hidden="First Name" name="first_name">
            <input type="text" hidden="Second Name" name="second_name">
            <button type="submit"> <fmt:message key="edit_profile" /></button>
        </form>
    </div>
</tags:general>

