<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>news-Control</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="text-center">
    <h1>Change news and orders!</h1>
</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/admin/admin-page">
                    Admin main page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/admin/admin-page">
                    Admin main page</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="text-center">
    <h3>Edit news here</h3>
</div>
<div class="container">
    <div class="table">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Edit News Head</th>
                <th>Edit News Body</th>
                <th>Edit button</th>
            </tr>
            </thead>
            <c:forEach var="newsDto" items="${newsDto}">
                <tbody>
                <tr>
                    <th>${newsDto.newsHead}</th>
                    <th>${newsDto.newsBody}</th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/news-comment-control/edit"
                              id="editForm" method="post"></form>
                    </th>
                </tr>
                <tr>
                    <th><input class="input-sm" form="editForm" name="newsHead"></th>
                    <th><input class="input-lg" form="editForm" name="newsBody"></th>
                    <th><input type="hidden" form="editForm" value="${newsDto.newsId}" name="newsId"></th>
                    <th><input type="submit" class="btn btn-danger" form="editForm" value="Edit"></th>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
<div class="text-center">
    <h3>Delete news Here</h3>
</div>
<div class="container">
    <div class="table-bordered">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>News Head</th>
                <th>News Body</th>
                <th>Delete button</th>
            </tr>
            </thead>
            <c:forEach var="newsDto" items="${newsDto}">
                <tbody>
                <tr>
                    <th>${newsDto.newsHead}</th>
                    <th>${newsDto.newsBody}</th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/news-comment-control/delete-news"
                              method="post">
                            <input type="hidden" name="newsItemId" value="${newsDto.newsId}">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </form>
                    </th>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
<div class="text-center">
    <h3>Delete comment here</h3>
</div>
<div class="container">
    <div class="table-bordered">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Commentators nick</th>
                <th>News Head</th>
                <th>News Id</th>
                <th>Comment</th>
                <th>Delete button</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="news" items="${newsDto}">
                <c:forEach items="${news.commentDtoList}" var="comment">
                    <tr>
                        <th>${comment.userDto.userLogin}</th>
                        <th>${news.newsHead}</th>
                        <th>${news.newsId}</th>
                        <th>${comment.commentBody}</th>
                        <th>
                            <form action="${pageContext.request.contextPath}
                            /admin/news-comment-control/delete-comment" method="post">
                                <input type="hidden" name="commentId"
                                       value="${comment.commentId}">
                                <input type="submit" class="btn btn-danger" value="Delete">
                            </form>
                        </th>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<form action="${pageContext.request.contextPath}
        /admin/news-comment-control/add-news" id="addNewsForm" method="post"></form>
<div class="text-center">
    <h3>Add new piece of news here</h3>
</div>
<div class="container">
    <div class="table table-bordered">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>News Head</th>
                <th>News Body</th>
                <th>Add button</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <input class="input-sm form-control" name="newsHead"
                           form="addNewsForm" placeholder="News head" required>
                </th>
                <th>
                    <textarea class="textarea form-control" name="newsBody" form="addNewsForm"
                              rows="4" placeholder="News body" required></textarea>
                </th>
                <th>
                    <input type="submit" class="btn btn-danger" value="Add" form="addNewsForm">
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<%
    String action = request.getParameter("action");
    if (action != null) {
        switch (action) {
            case "editSuccess":
                out.print("<p align=\"center\"> News edited successfully </p>");
            case "deleteCommentSuccess":
                out.print("<p align=\"center\"> Comment deleted successfully </p>");
            case "newsSuccess":
                out.print("<p align=\"center\"> Piece of news added successfully </p>");
        }
    }
%>
</body>
</html>
