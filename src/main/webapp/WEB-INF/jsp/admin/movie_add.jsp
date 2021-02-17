<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add Movie - Admin</title>
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
        <h1 align="center"> Add Movie</h1>
        <form action="movie_add.do" method="post">
            <!-- onsubmit= "return movieValidate(this.login.value, this.password.value);" -->
            <div class="form-group">
                <input type="text" name="title" class="form-control" placeholder="title">
            </div>
            <div class="form-group">
                <input type="text" name="country" class="form-control" placeholder="country">
            </div>
            <div class="form-group">
                <input type="text" name="year" class="form-control" placeholder="year">
            </div>
            <div class="form-group ">
                <select class="form-control" name="genre">
                    <option value="DETECTIVE">detective</option>
                    <option value="DRAMA">drama</option>
                    <option value="MUSICAL">musical</option>
                    <option value="ADVENTURE">adventure</option>
                    <option value="FANTASTIC">fantastic</option>
                    <option value="HORROR">horror</option>
                    <option value="COMEDY" selected>comedy</option>
                </select>
            </div>
            <div class="form-group ">
                <select  class="form-control" name="movie_type">
                    <option value="FILM" selected>film</option>
                    <option value="SERIES" selected>series</option>
                    <option value="ANIME" selected>anime</option>
                    <option value="CARTOON" selected>cartoon</option>
                </select>
            </div>

            <div class="form-group">
                <input type="text" name="age_category" class="form-control" placeholder="age category">
            </div>
            <div class="form-group">
                <textarea class="form-control" name="description" placeholder="description"></textarea>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="youtube_trailer" placeholder="youtube trailer hash">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="image_path" placeholder="image path (https)">
            </div>
            <button type="submit" class="btn btn-primary">Add Movie</button>
        </form>
    </div>
</div>
</body>
</html>
