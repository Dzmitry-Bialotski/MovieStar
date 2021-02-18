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
                <h5 align="justify">${movie.description}</h5>
            </div>
            <h1 align="center">*Здесь будет звездночный рейтинг*</h1>
            <div class="simple-rating">
                <div class="simple-rating__items">
                    <input id="simple-rating__5" type="radio" class="simple-rating__item" checked name="simple-rating" value="5">
                    <label for="simple-rating__5" class="simple-rating__label"></label>
                    <input id="simple-rating__4" type="radio" class="simple-rating__item" name="simple-rating" value="4">
                    <label for="simple-rating__4" class="simple-rating__label"></label>
                    <input id="simple-rating__3" type="radio" class="simple-rating__item" name="simple-rating" value="3">
                    <label for="simple-rating__3" class="simple-rating__label"></label>
                    <input id="simple-rating__2" type="radio" class="simple-rating__item" name="simple-rating" value="2">
                    <label for="simple-rating__2" class="simple-rating__label"></label>
                    <input id="simple-rating__1" type="radio" class="simple-rating__item" name="simple-rating" value="1">
                    <label for="simple-rating__1" class="simple-rating__label"></label>

                </div>
            </div>
            <h2 align="center">*Reviews*</h2>
            <div class="review-section">
                <c:forEach var="review" items="${requestScope.movie.reviews}">
                    <div class="review-container my-2 p-2">
                        <div class="review-user-info row">
                            <div class="review-user-img-container col-sm-2 my-1 ml-3">
                                <c:if test="${not empty review.userAvatarPath }">
                                    <img class="avatar-medium avatar-round mr-1"
                                         src="${pageContext.request.contextPath}${review.userAvatarPath}" >
                                </c:if>
                                <c:if test="${empty review.userAvatarPath}">
                                    <img class="avatar-medium avatar-round mr-1"
                                         src="${pageContext.request.contextPath}${sessionScope.defaultAvatarPath}" >
                                </c:if>
                            </div>
                            <div class="review-user-login col-sm-6 m-1">
                                <h2> ${review.userLogin}</h2>
                            </div>
                            <div class="review-user-like col-sm-1 mt-3">
                                <button class="btn btn-success like-btn">
                                    <i class="far fa-thumbs-up like"></i>
                                    <div class="likes-num"> 0 </div>
                                </button>
                            </div>
                            <div class="review-user-like col-sm-1 mt-3 dislike">
                                <button class="btn btn-danger like-btn">
                                    <i class="far fa-thumbs-down like"></i>
                                    <div class="likes-num"> 0 </div>
                                </button>
                            </div>
                        </div>
                        <div class="review-text my-2">
                            <p><h4> ${review.text}</h4></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

</tags:general>





