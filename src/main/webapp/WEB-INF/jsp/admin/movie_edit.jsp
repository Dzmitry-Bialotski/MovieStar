<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Edit Movie - Admin</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <span class="navbar-brand">MovieStar</span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin.do">Admin</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Manage
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin_users.do">Users</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin_reviews.do">Reviews</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin_movies.do">Movies</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin">Help</a>
                    </div>
                </li>
            </ul>
            <a class="nav-link text-light" href="${pageContext.request.contextPath}/home.do">Back to MovieStar</a>
        </div>
    </div>

</nav>
<div class="container">
    <div class="movie-form">
        <h1 align="center"> Edit Movie</h1>
        <form action="movie_edit.do" method="post">
            <input type="hidden" id="genre" value="${requestScope.movie.genre}">
            <input type="hidden" id="movie_type" value="${requestScope.movie.movieType}">
            <input type="hidden" id="description" value="${requestScope.movie.description}">
            <!-- onsubmit= "return movieValidate(this.login.value, this.password.value);" -->
            <input type="hidden" name="movieId" value="${requestScope.movie.id}">
            <div class="form-group">
                <input type="text" name="title" class="form-control" placeholder="title"
                       value="${requestScope.movie.title}">
            </div>
            <div class="form-group">
                <input type="text" name="country" class="form-control" placeholder="country"
                       value="${requestScope.movie.country}">
            </div>
            <div class="form-group">
                <input type="text" name="year" class="form-control" placeholder="year"
                       value="${requestScope.movie.year}">
            </div>
            <div class="form-group ">
                <select name ="genre" class="form-control" id="select-genre">
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
            <div class="form-group ">
                <select name="movie_type" class="form-control" id="select-movie_type">
                    <option value="" selected><fmt:message key="MovieType" />...</option>
                    <option value="FILM"><fmt:message key="FILM" /></option>
                    <option value="SERIES"><fmt:message key="SERIES" /></option>
                    <option value="ANIME"><fmt:message key="ANIME" /></option>
                    <option value="CARTOON"><fmt:message key="CARTOON" /></option>
                </select>
            </div>

            <div class="form-group">
                <input type="text" name="age_category" class="form-control" placeholder="age category"
                       value="${requestScope.movie.ageCategory}">
            </div>
            <div class="form-group">
                <textarea class="form-control" name="description" placeholder="description" id="aria-description"
                          value="${requestScope.movie.description}"></textarea>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="youtube_trailer" placeholder="youtube trailer hash"
                       value="${requestScope.movie.youtubeTrailer}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="image_path" placeholder="image path (https)"
                       value="${requestScope.movie.imagePath}">
            </div>
            <button type="submit" class="btn btn-primary">Edit Movie</button>
        </form>
    </div>
</div>
</body>
<script>
    const genre = document.getElementById('genre').value;
    const select_genre = document.querySelector('#select-genre').getElementsByTagName('option');
    for (let i = 0; i < select_genre.length; i++) {
        if (select_genre[i].value === genre) select_genre[i].selected = true;
    }

    const movie_type = document.getElementById('movie_type').value;
    const select_movie_type = document.querySelector('#select-movie_type').getElementsByTagName('option');
    for (let i = 0; i < select_movie_type.length; i++) {
        if (select_movie_type[i].value === movie_type) select_movie_type[i].selected = true;
    }

    const description = document.getElementById('description').value;
    const area = document.querySelector('#aria-description');
    area.value = description;
</script>
</html>

