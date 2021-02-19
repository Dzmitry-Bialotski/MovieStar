<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title><fmt:message key="register"/> - MovieStar</title>
    <jsp:include page="/WEB-INF/jsp/shared/style_import.jsp"/>
</head>
<body class="login_body">
<jsp:include page="/WEB-INF/jsp/shared/header.jsp"/>
<body>
<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3><fmt:message key="register"/></h3>
            </div>
            <div class="card-body">
                <form action="register.do" method="post" onsubmit=
                        "return registerValidate(this.login.value, this.password.value, this.passwordConfirm.value);">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" name="login" placeholder="<fmt:message key="username"/>">

                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="password"/>">
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <input type="password" name="passwordConfirm" class="form-control" placeholder="<fmt:message key="passwordConfirm"/>">
                    </div>

                    <div class="form-group">
                        <input type="submit" value="<fmt:message key="register"/>" class="btn float-right login_btn">
                    </div>
                </form>
                <div class="text-danger error">
                    <c:if test="${errorMessage != \"\"}">
                        ${errorMessage}
                    </c:if>
                </div>

            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    <fmt:message key="do_have_account"/>
                    <a href="${pageContext.request.contextPath}/login" class="ml-2">
                        <fmt:message key="login_task"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/WEB-INF/jsp/shared/scripts_import.jsp"/>

</body>
</html>
