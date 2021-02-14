<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<tags:general title="movie">
    <div class="section text-light m-1 p-2">
        imagePath;
        title;
        country;
        year;
        genre;
        movieType;
        ageCategory;
        shortDescription;
        description;
        youtubeTrailer;

            Trailer
        <iframe align="center" class="m-2" width="560" height="315" frameborder="0"
                src="https://www.youtube.com/embed/-El5Sb_EaIc"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                allowfullscreen>
        </iframe>
    </div>

</tags:general>
