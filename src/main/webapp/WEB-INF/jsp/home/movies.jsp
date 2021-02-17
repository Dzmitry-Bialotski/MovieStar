<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="movie">
    <div class="section">
        <div class="movie-list text-light p-2 row">
            <c:forEach var="movie" items="${requestScope.movies}">
                <div class="movie-item m-1 p-1 col-sm">
                    <div class="movie-image-container">
                        <a href="${pageContext.request.contextPath}/movie.do?movieId=${movie.id}">
                            <img class="movie-item__image" src="${movie.imagePath}" alt="Image">
                        </a>
                    </div>
                    <div class="movie-item__info">
                        <a href="${pageContext.request.contextPath}/movie.do?movieId=${movie.id}">
                            <h2>${movie.title}</h2>
                            <h6>${movie.country}, ${movie.year}, ${movie.ageCategory}+</h6>
                            <h6>${movie.movieType.toString()}, ${movie.genre.toString()}</h6>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</tags:general>
