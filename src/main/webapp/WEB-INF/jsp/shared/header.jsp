<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language" scope="application"/>
<nav class="navbar navbar-expand-lg navbar-dark custom-nav">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07"
                aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <img class ="logo_image" src="${pageContext.request.contextPath}/img/logo/moviestar_logo.png"/>
        <a class="navbar-brand" href="#">MovieStar</a>
        <div class="collapse navbar-collapse" id="navbarsExample07">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Movies</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdown07" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Language</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown07">
                        <a class="dropdown-item" href="change_locale_command.do?currentLocale=ru"><fmt:message key="ru" /></a>
                        <a class="dropdown-item" href="change_locale_command.do?currentLocale=en"><fmt:message key="en" /></a>
                    </div>
                </li>
            </ul>
            <a href="${pageContext.request.contextPath}/login">
                <button class="btn btn-primary"> <fmt:message key="login"/> </button>
            </a>
            <a href="${pageContext.request.contextPath}/register">
                <button class="btn btn-success"> <fmt:message key="register"/> </button>
            </a>
        </div>
    </div>
</nav>
