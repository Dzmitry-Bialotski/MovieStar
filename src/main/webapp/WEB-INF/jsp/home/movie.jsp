<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="custom_tags" prefix="ctg" %>
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
            <form method="post" action="rating.do">
                <input type="hidden" name="movieId" value="${movie.id}">
                <div class="rating rating_set">
                    <div class="rating__body">
                        <div class="rating__active"></div>
                        <div class="rating__items">
                            <input type="radio" class="rating__item" value="1" name="rating">
                            <input type="radio" class="rating__item" value="2" name="rating">
                            <input type="radio" class="rating__item" value="3" name="rating">
                            <input type="radio" class="rating__item" value="4" name="rating">
                            <input type="radio" class="rating__item" value="5" name="rating">
                            <input type="radio" class="rating__item" value="6" name="rating">
                            <input type="radio" class="rating__item" value="7" name="rating">
                            <input type="radio" class="rating__item" value="8" name="rating">
                            <input type="radio" class="rating__item" value="9" name="rating">
                            <input type="radio" class="rating__item" value="10" name="rating">
                        </div>
                    </div>
                    <div class="rating__value">6.4</div>
                </div>
                <button type="submit send-rating-btn btn btn-secondary m-1">Send rating</button>
            </form>

            <h2 align="center">*Reviews*</h2>
            <ctg:accessRole accessRole="REVIEWER">
                <form method="post" action="review_add.do">
                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                    <input type="hidden" name="movieId" value="${movie.id}">
                    <textarea name="text" placeholder="write your review hear"></textarea>
                    <button type="submit"> Send review </button>
                </form>
            </ctg:accessRole>
            <div class="review-section">
                <c:forEach var="review" items="${requestScope.movie.reviews}">
                    <div class="review-container my-2 p-2">
                        <div class="review-user-info row">
                            <div class="review-user-img-container col-sm-2 my-1 ml-3">
                                <c:if test="${not empty review.userAvatarPath }">
                                    <img class="avatar-medium avatar-round mr-1"
                                         src="${pageContext.request.contextPath}/provide_image.do?file_name=${review.userAvatarPath}">
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
                        <ctg:accessUser userId="${review.userId}">
                            <form method="post" action="review_delete.do">
                                <input type="hidden" name="reviewId" value="${review.id}">
                                <button type="submit" class="delete_btn btn btn-danger text-light">delete review</button>
                            </form>
                        </ctg:accessUser>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</tags:general>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rating.js"></script>