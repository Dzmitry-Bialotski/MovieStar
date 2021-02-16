<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="home">
    <div class="section movie-section text-light my-1 p-2">
        <div class="movie-overview">
            <div class="movie-info my-1">
                <h2 align="center">${movie.title} (${movie.year})</h2>
                <h6 align="center">
                        ${movie.country}, ${movie.ageCategory}+, ${movie.movieType.toString()}, ${movie.genre.toString()}
                </h6>
            </div>
            <div class="youtube-trailer my-1">
                <iframe width="560" height="315"
                        src="https://www.youtube.com/embed/${requestScope.movie.youtubeTrailer}" frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowfullscreen>
                </iframe>
            </div>
            <div class="movie-description my-1">
                <h1 align="center">Описание</h1>
                <h5>${movie.description}</h5>
            </div>
            <h1 align="center">*Здесь будет звездночный рейтинг*</h1>
        </div>
    </div>

</tags:general>





