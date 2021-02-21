<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="movie">
    <jsp:include page="/WEB-INF/jsp/home/search.jsp"/>
    <div class="section pl-3 mt-2">
        <div class="movie-list text-light p-2 row">
            <c:forEach var="movie" items="${requestScope.movies}">
                <div class="movie-item m-1 p-1 col-sm">
                    <a href="${pageContext.request.contextPath}/movie.do?movieId=${movie.id}">
                    <div class="movie-image-container movie-holder">

                            <div class="movie-block">
                                <h4 align="center"> Rating - ${requestScope.movie.rating} <span style="color: yellow">&#9733;</span></h4>
                            </div>
                            <div class="movie-image-cover"></div>
                            <img class="movie-item__image" src="${movie.imagePath}" alt="Image">
                    </div>
                    <div class="movie-item__info">
                            <h3>${movie.title}</h3>
                            <h6>${movie.country}, ${movie.year}, ${movie.ageCategory}+</h6>
                            <h6>${movie.movieType.toString()}, ${movie.genre.toString()}</h6>

                    </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>

</tags:general>
