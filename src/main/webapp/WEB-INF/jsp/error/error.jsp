<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLocale}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Error Page</title></head>
<body>
<div class="container">
    <div class="row">
        <div class="col-8 offset-2 text">
            Status code: ${pageContext.errorData.statusCode}
        </div>
    </div>
    <div class="mb-5 row">
        <div class="col-8 offset-2 text">
            Exception: ${pageContext.errorData.throwable}
        </div>
    </div>
</div>
<style>
    body {
        background-color: rgba(255, 255, 255, 0.60);
    }
    .text {
        font-size: 30px;
        color: #fd1e1e;
        display: flex;
        justify-content: center;
    }
</style>
</body>
</html>