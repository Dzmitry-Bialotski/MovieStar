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
                        ${movie.country}, ${movie.ageCategory}+, <fmt:message key="${movie.movieType.toString()}"/>, <fmt:message key="${movie.genre.toString()}"/>
                </h6>
                <h4 align="center"> <fmt:message key="Rating"/> - ${requestScope.movie.rating} <span style="color: yellow">&#9733;</span></h4>
            </div>
            <div class="youtube-trailer my-1">
                <iframe width="560" height="315"
                        src="https://www.youtube.com/embed/${requestScope.movie.youtubeTrailer}" frameborder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowfullscreen>
                </iframe>
            </div>
            <div class="movie-description my-1">
                <h1 align="center"><fmt:message key="Description"/></h1>
                <h5 align="justify">${movie.description}</h5>
            </div>
            <ctg:accessRole accessRole="SPECTATOR">
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
                        <div class="rating__value">${requestScope.movie.rating}</div>
                    </div>
                    <button type="submit" class="send-rating-btn btn btn-success m-1"><fmt:message key="SendRating"/></button>
                </form>
            </ctg:accessRole>
            <h3 align="center"><fmt:message key="Reviews"/></h3>
            <div class="review-section">
                <ctg:accessRole accessRole="REVIEWER">
                    <form method="post" action="review_add.do" class="form-inline my-2 my-lg-0 review-form">
                        <input type="hidden" name="userId" value="${sessionScope.user.id}">
                        <input type="hidden" name="movieId" value="${movie.id}">
                        <textarea name="text" placeholder="<fmt:message key="WriteReview"/>" class="form-control mr-sm-2 review-textarea"></textarea>
                        <button type="submit" class="btn btn-primary my-2 my-sm-0 send-review-btn"> <fmt:message key="SendReview"/> </button>
                    </form>
                </ctg:accessRole>
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
                            <!-- likes -->
                            <ctg:accessRole accessRole="SPECTATOR">
                                <div class="review-user-like col-sm-1 mt-3">
                                    <form method="post" action="like.ajax" id="like-form-${review.id}" class="like-form">
                                        <input type="hidden" name="reviewId" value="${review.id}" class="like-input-id">
                                        <button class="btn btn-success like-btn" id="like-btn-${review.id}" type="submit">
                                            <i class="far fa-thumbs-up like"></i>
                                            <div class="likes-num" id="likes-num-${review.id}" > ${review.likes} </div>
                                        </button>
                                    </form>
                                </div>
                                <!-- dislikes -->
                                <div class="review-user-like col-sm-1 mt-3 dislike" >
                                    <form method="post" action="dislike.ajax" id="dislike-form-${review.id}" class="dislike-form">
                                        <input type="hidden" name="reviewId" value="${review.id}" class="dislike-input-id">
                                        <button class="btn btn-danger like-btn" id="dislike-btn-${review.id}" type="submit">
                                            <i class="far fa-thumbs-down like"></i>
                                            <div class="likes-num" id="dislikes-num-${review.id}"> ${review.dislikes} </div>
                                        </button>
                                    </form>
                                </div>
                            </ctg:accessRole>
                        </div>
                        <div class="row">
                            <div class="review-text my-2 col-sm-9">
                                <h4 align="justify"> ${review.text}</h4>
                            </div>
                            <ctg:accessUser userId="${review.userId}">
                                <form method="post" action="review_delete.do" class="col-sm-3">
                                    <input type="hidden" name="reviewId" value="${review.id}">
                                    <button type="submit" class="delete_btn btn btn-outline-danger text-light mr-2"><fmt:message key="DeleteReview"/></button>
                                </form>
                            </ctg:accessUser>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</tags:general>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rating.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/like.js"></script>