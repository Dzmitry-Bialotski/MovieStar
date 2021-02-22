<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<div class="section text-light m-1 py-2">
    <form class="form-inline m-2 my-lg-0" action="movies.do" method="get">
        <input type="hidden" id="genre" value="${requestScope.genre}">
        <input type="hidden" id="age_category" value="${requestScope.age_category}">
        <input type="hidden" id="movie_type" value="${requestScope.movie_type}">
        <input class="form-control mr-sm-2 search-input" name="search" type="search"
               value="${requestScope.search}" placeholder="<fmt:message key="search" />" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search" /></button>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name ="genre"  class="dropdown-select rounded" id="select-genre">
                <option value="" selected><fmt:message key="Genre" />...</option>
                <option value="ACTION"><fmt:message key="ACTION" /></option>
                <option value="DETECTIVE"><fmt:message key="DETECTIVE" /></option>
                <option value="DRAMA"><fmt:message key="DRAMA" /></option>
                <option value="MUSICAL"><fmt:message key="MUSICAL" /></option>
                <option value="ADVENTURE"><fmt:message key="ADVENTURE" /></option>
                <option value="FANTASTIC"><fmt:message key="FANTASTIC" /></option>
                <option value="HORROR"><fmt:message key="HORROR" /></option>
                <option value="COMEDY"><fmt:message key="COMEDY" /></option>
            </select>
        </div>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name="movie_type" class="dropdown-select rounded" id="select-movie_type">
                <option value="" selected><fmt:message key="MovieType" />...</option>
                <option value="FILM"><fmt:message key="FILM" /></option>
                <option value="SERIES"><fmt:message key="SERIES" /></option>
                <option value="ANIME"><fmt:message key="ANIME" /></option>
                <option value="CARTOON"><fmt:message key="CARTOON" /></option>
            </select>
        </div>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name="age_category" class="dropdown-select rounded" id="select-age_category">
                <option value="" selected><fmt:message key="AgeCategory" />...</option>
                <option value="0">0+</option>
                <option value="6">6+</option>
                <option value="12">12+</option>
                <option value="16">16+</option>
                <option value="18">18+</option>
            </select>
        </div>
    </form>
</div>