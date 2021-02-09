<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language" scope="application"/>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<nav class="navbar navbar-expand-lg navbar-dark custom-nav">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample07"
                aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home.do">
            <img class ="logo_image" src="${pageContext.request.contextPath}/img/logo/moviestar_logo.png"/>
            MovieStar
        </a>
        <div class="collapse navbar-collapse" id="navbarsExample07">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile.do"> <i class="fa fa-user" aria-hidden="true"></i> <fmt:message key="profile" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="random"> <i class="fa fa-film mr-1" aria-hidden="true"></i><fmt:message key="movies" /></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="dropdown07" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-globe" aria-hidden="true"></i> <fmt:message key="language" />
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdown07">
                        <a class="dropdown-item" href="change_locale_command.do?currentLocale=ru&returnUrl=${pageContext.request.requestURL}">
                            <img class="flag_img" src="${pageContext.request.contextPath}/img/flag/ru.png">
                            <fmt:message key="ru" />
                        </a>
                        <a class="dropdown-item" href="change_locale_command.do?currentLocale=en&returnUrl=${pageContext.request.requestURL}">
                            <img class="flag_img" src="${pageContext.request.contextPath}/img/flag/en.png" >
                            <fmt:message key="en" />
                        </a>
                    </div>
                </li>
            </ul>
            <div class="navbar-nav">
                <tags:user-navbar/>
            </div>
        </div>
    </div>
</nav>
