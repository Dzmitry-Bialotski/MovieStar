<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="home">
    <div class="section text-light m-1 p-2">
        <h1 align="center">Welcome to MovieStar</h1>
        <h3>
            <p>Here is some rules you have to know about that site.
            If you are here at the first time, and you haven`t already registered/login,
            you are <span class="yellow">GUEST</span>. That means that you can only watch information about different movies.
            Once you registered and logged in, you are <span class="yellow">SPECTATOR</span>, and you can edit your profile,
            place avatar, place your own rating to movie, or confirm email to become <span class="yellow">REVIEWER</span> .
            Once you did it, you gain the ability to review movies!! To become the <span class="yellow">ADMINISTRATOR</span>
            you have to write one your own to owner of that web site. Keep in mind
            that <span class="yellow">ADMINISTRATOR</span> can block any user or any review, if the last is not appropriate.
            Be polite and respect the rules of propriety!</p>
        </h3>

    </div>
    <div class="youtube-trailer" style="width : 560px; margin-left: auto; margin-right: auto;">
        <iframe class="m-2" width="560" height="315" frameborder="0"
                src="https://www.youtube.com/embed/-El5Sb_EaIc"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen>
        </iframe>
    </div>


</tags:general>
