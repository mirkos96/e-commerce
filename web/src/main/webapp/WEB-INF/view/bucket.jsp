<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.maks.service.modelDto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>bucket</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
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
<%UserDto userDto = (UserDto) session.getAttribute("userDto");%>
<div id="header">
    <div class="container text-center">
        <h1>Online pizza store</h1>
        <h3>Eat, Drink, Be happy!</h3>
        <h3>Your personal bucket here!</h3>
        <h4><%out.print(userDto.getUserLogin());%></h4>
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/user/news">Latest News</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/contacts">Contact us</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/user/personal-cabinet">
                        <span class="glyphicon glyphicon-user"></span>
                        <%out.print(userDto.getUserLogin());%> Account</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/main-page">Main page</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container" id="pre-order-table">
    <p class="text-center, text-primary" align="center">
        <mark>Your bucket</mark>
    </p>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Pizza</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Price</th>
            <th>Change Here</th>
        </tr>
        </thead>
        <c:forEach var="item" items="${listOfItems}">
            <tbody>
            <tr>
                <th>${item.orderItemName}</th>
                <th>${item.orderItemDescription}</th>
                <th>${item.orderItemAmount}</th>
                <th>${item.orderItemPrice}</th>
                <th>
                    <div class="container-fluid">
                        <button class="btn btn-info" data-toggle="modal" data-target=
                                "#${item.orderItemId}">Change
                        </button>
                        <div id="${item.orderItemId}" class="modal fade">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">${item.orderItemName}</h4>
                                        <h4 class="modal-title">${item.orderItemPrice} Br</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form action="${pageContext.request.contextPath}/user/bucket/change-order"
                                              method="post">
                                            <input type="hidden" value="${item.orderItemName}" name="orderItemName">
                                            <input placeholder="Enter new amount"
                                                   data-toggle="tooltip"
                                                   title="Unfortunately this is the only thing you can change."
                                                   name="newAmount" required>
                                            <input type="submit" class="btn btn-primary" value="Change">
                                        </form>
                                        <form action="${pageContext.request.contextPath}/user/bucket/delete-order"
                                              method="post">
                                            <input name="orderNameToDelete" type="hidden" value="${item.orderItemName}">
                                            <input type="submit" class="btn btn-danger" value="Delete order">
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </th>
            </tr>
            </tbody>
        </c:forEach>
        <tfoot>
        <tr>
            <th>Total price</th>
            <th>-</th>
            <th>-</th>
            <th><c:out value="${totalPrice}"/> Br</th>
            <th>
                <c:choose>
                    <c:when test="${listOfItems.size()==0}">
                        <form action="${pageContext.request.contextPath}/user/bucket/make-an-order"
                              method="post">
                            <button class="btn btn-danger" disabled data-toggle="tooltip"
                                    title="Might be pressed only when you have something to add">
                                Make an order
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="${pageContext.request.contextPath}/user/bucket/make-an-order"
                              method="post">
                            <button class="btn btn-danger">
                                Make an order
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </th>
        </tr>
        </tfoot>
    </table>
</div>
<div class="container" id="full-order-container">
    <p class="text-center, text-primary" align="center">
        <mark>Your orders</mark>
    </p>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Order number</th>
            <th>Total price</th>
            <th>Time</th>
            <th>Status</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach var="order" items="${orderDtoList}">
            <tbody>
            <tr>
                <th>${order.orderNumber}</th>
                <th>${order.price}</th>
                <th>${order.date}</th>
                <th>${order.orderStatus}</th>
                <th><c:choose>
                    <c:when test="${'NEW'.equals(order.orderStatus.name())}">
                        <div class="container-fluid">
                            <form action="${pageContext.request.contextPath}/user/bucket/delete-not-confirmed-order"
                                  method="post">
                                <input name="orderIdToDelete" type="hidden" value="${order.orderId}">
                                <button class="btn btn-danger">
                                    Delete
                                </button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="container-fluid">
                                <button class="btn btn-danger" disabled data-toggle="tooltip"
                                title="Might be canceled or deleted only if status is NEW">
                                    Delete
                                </button>
                        </div>
                    </c:otherwise>
                </c:choose>
                </th>
            </tr>
            </tbody>
        </c:forEach>
    </table>

</div>
<div id="footBlack">
    <h4><br>Questions and suggestions? Contact us via our email:
        hotPizza@gmail.com</h4>
    <h4><br>Designed by MaksiDev</h4>
    <h5><br>License of 01.01.2018</h5>
</div>
</body>
</html>
