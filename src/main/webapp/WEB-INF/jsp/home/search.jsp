<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<div class="section text-light m-1 py-2">
    <form class="form-inline m-2 my-lg-0">
        <input class="form-control mr-sm-2 search-input" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name="two" class="dropdown-select rounded">
                <option value="">Select Genre...</option>
                <option value="ACTION">ACTION</option>
                <option value="DETECTIVE">DETECTIVE</option>
                <option value="DRAMA">DRAMA</option>
                <option value="MUSICAL">MUSICAL</option>
                <option value="ADVENTURE">ADVENTURE</option>
                <option value="FANTASTIC">FANTASTIC</option>
                <option value="HORROR">HORROR</option>
                <option value="COMEDY">COMEDY</option>
            </select>
        </div>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name="two" class="dropdown-select rounded">
                <option value="">Select Movie Type...</option>
                <option value="FILM">FILM</option>
                <option value="SERIES">SERIES</option>
                <option value="ANIME">ANIME</option>
                <option value="CARTOON">CARTOON</option>
            </select>
        </div>
        <div class="dropdown dropdown-dark my-1 ml-2">
            <select name="two" class="dropdown-select rounded">
                <option value="">Select Age Category...</option>
                <option value="0">0+</option>
                <option value="6">6+</option>
                <option value="12">12+</option>
                <option value="16">16+</option>
                <option value="18">18+</option>
            </select>
        </div>
    </form>
</div>