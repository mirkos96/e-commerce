<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>admin-page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container text-center">
    <h1>Online pizza store</h1>
    <h1>Rule the will of users!</h1>
    <h3>Admin page!</h3>
    <h4>${userDto.userLogin}</h4>
</div>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/admin/message-control">Messages</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="table table-bordered">
        <table class="table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Button</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th><p class="text-primary">Control over orders</p></th>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/order-status-control">
                        <input type="button" class="btn btn-danger" value="Orders">
                    </a>
                </th>
            </tr>
            <tr>
                <th><p class="text-primary">Control over news and comments</p></th>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/news-comment-control">
                        <input type="button" class="btn btn-danger" value="News">
                    </a>
                </th>
            </tr>
            <tr>
                <th><p class="text-primary">Control over users</p></th>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/user-control">
                        <input type="button" class="btn btn-danger" value="Users">
                    </a>
                </th>
            </tr>
            <tr>
                <th><p class="text-primary">Control over order items</p></th>
                <th>
                    <a href="${pageContext.request.contextPath}/admin/order-item/add">
                        <input type="submit" class="btn btn-danger" value="Add new item">
                    </a>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="text-center">
    <h2 align="center">Admin doesn't need a nice page!</h2>
</div>
</body>
</html>
