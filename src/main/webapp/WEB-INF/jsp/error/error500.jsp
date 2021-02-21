<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="error_500.title">
    <div class="col align-self-center text-center my-3 p-5 section">
        <p class="h1 text-danger" ><fmt:message key="error_500.title"/> </p>
        <p class="h2 text-danger"> <fmt:message key="error_500.general"/> </p>
        <p class="h2 text-danger"> <fmt:message key="error_500.hint"/> </p>
    </div>
</tags:general>