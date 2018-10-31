<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <title>news</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script async src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script async src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <style>
        #header {
            margin-bottom: 0;
        }

        #footBlack {
            margin-bottom: 0;
            background-color: black;
            text-align: right;
            padding: 0 20px 10px 0;
        }
    </style>
</head>
<body>
<div id="header">
    <div class="container text-center">
        <h1>Online pizza store</h1>
        <h3>Eat, Drink, Be happy!</h3>
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/user/contacts">Contact us</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/user/personal-cabinet">
                        <span class="glyphicon glyphicon-user"></span>
                        ${userDto.userLogin} Account</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/main-page">Main page</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container-fluid" align="center">
    <h3>We are situated only in internet</h3>
    <h3>Latest news for our customers</h3>
    <h3>Discounts prises and competitions!</h3>
    <h3>Do not miss!</h3>
</div>

<div class="container-fluid">
    <c:forEach items="${newsAndCommentList}" var="newsItem">
        <table class="table table-bordered">
            <thead>
            <tr>
                <td align="center">${newsItem.newsHead}</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td align="center">${newsItem.newsBody}</td>
            </tr>
            </tbody>
            <tfoot>
            <c:forEach items="${newsItem.commentDtoList}" var="comment">
                <tr>
                    <td>${comment.userDto.nameSurname} says:</td>
                </tr>
                <tr>
                    <td align="center">${comment.commentBody}</td>
                </tr>
            </c:forEach>
            <tr>
                <th>
                    <form action="${pageContext.request.contextPath}/user/news/add-comment" method="post">
                        <label for="comment">Add comment here</label>
                        <input type="hidden" value="${newsItem.newsId}" name="newsId">
                        <input type="hidden" value="${userDto.userLogin}" name="userLogin">
                        <textarea class="form-control" id="comment" rows="3" data-toggle="tooltip"
                                  title="Be careful only 250 symbols are allowed"
                                  placeholder="Add a comment" name="commentBody">
                        </textarea>
                        <input type="submit" align="right" class="btn btn-danger" value="Add comment">
                    </form>
                </th>
            </tr>
            </tfoot>
        </table>
    </c:forEach>
</div>

<div id="footBlack">
    <h4><br>Questions and suggestions? Contact us via our email:
        hotPizza@gmail.com</h4>
    <h4><br>Designed by MaksiDev</h4>
    <h5><br>License of 01.01.2018</h5>
</div>
</body>
</html>

