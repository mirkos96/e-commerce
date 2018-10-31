<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>order-Control</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>
<body>
<div class="text-center">
    <h1>Change user's orders!</h1>
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
<div class="container">
    <div class="table table-bordered">
        <table class="table">
            <thead>
            <tr>
                <th>Order number</th>
                <th>Total price</th>
                <th>Status</th>
                <th>Reviewing</th>
                <th>In progress</th>
                <th>Delivered</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <th>${order.orderNumber}</th>
                    <th>${order.price}</th>
                    <th>${order.orderStatus}</th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/order-status-control/set-new-status"
                              method="post">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <input type="hidden" name="orderStatus" value="Reviewing">
                            <input type="submit" class="btn btn-danger" value="Reviewing">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/order-status-control/set-new-status"
                              method="post">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <input type="hidden" name="orderStatus" value="In_progress">
                            <input type="submit" class="btn btn-danger" value="In progress">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/order-status-control/set-new-status"
                              method="post">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <input type="hidden" name="orderStatus" value="Delivered">
                            <input type="submit" class="btn btn-danger" value="Delivered">
                        </form>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
