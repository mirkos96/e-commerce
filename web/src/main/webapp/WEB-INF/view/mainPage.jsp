<%@ page import="com.maks.service.modelDto.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <title>pizzaMenu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script async src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script async src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <style>
        #header {
            margin-bottom: 0;
        }

        .error {
            color: red;
            font-size: 0.9em;
            font-weight: bold;
        }

        #footer {
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
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/user/news">
                        Latest News</a></li>
                    <li><a href="${pageContext.request.contextPath}
                    /user/contacts">Contact us</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/user/personal-cabinet">
                        <span class="glyphicon glyphicon-user"></span>
                        <%out.print(userDto.getUserLogin());%> Account</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/bucket" target="_blank">
                        My shopping list</a></li>
                    <li><a> For now you've got: <%out.print(userDto.getItemDtoList().size());%>
                        item(s) inside!</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="container">
    <c:forEach var="orderItem" items="${items}">
        <div class="col-sm-4">
            <div class="panel panel-primary">
                <div class="panel-heading">${orderItem.orderItemName}</div>
                <div class="panel-body">
                    <img src="${orderItem.orderItemPictureName}" class="img-responsive"
                         alt="Image" width="100%">
                </div>
                <div class="panel-footer">
                    <button class="btn btn-info" data-toggle="modal" data-target="#${orderItem.orderItemId}">
                        Make On Order
                    </button>
                    <div id="${orderItem.orderItemId}" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">${orderItem.orderItemName}</h4>
                                    <h5 class="modal-title">${orderItem.orderItemDescription}</h5>
                                    <h6 class="modal-title">${orderItem.orderItemPrice}Br</h6>
                                </div>

                                <div class="modal-body">
                                    <form:form action="/user/main-page/add-item" method="post"
                                               modelAttribute="orderItemDto">
                                        You are going to add ${orderItem.orderItemName} to the list of your orders.
                                        <form:input path="orderItemId" type="hidden" value="${orderItem.orderItemId}"/>
                                        <form:input path="orderItemName" type="hidden"
                                                    value="${orderItem.orderItemName}"/>
                                        <form:input path="orderItemDescription" type="hidden"
                                                    value="${orderItem.orderItemDescription}"/>
                                        <form:input path="orderItemPrice" type="hidden"
                                                    value="${orderItem.orderItemPrice}"/>
                                        <p><form:input path="orderItemAmount" class="input-sm" name="orderItemAmount"
                                                       data-toggle="tooltip" title="Range between 1 and 10"
                                                       placeholder="Enter amount"/></p>
                                        <p><form:errors path="orderItemAmount" cssClass="error"/></p>
                                        <p><input type="submit" align="center"
                                                  class="btn btn-primary" value="Add"></p>
                                    </form:form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div id="footer">
    <div class="container text-center">
        <form method="get" action="${pageContext.request.contextPath}/user/main-page">
            <table align="center">
                <tr>
                    <c:forEach begin="1" end="${numberOfPages}" varStatus="loop">
                        <th><a><a href="${pageContext.request.contextPath}
                        /user/main-page?pageNum=${loop.index}"> ${loop.index} </a></a></th>
                        ..
                    </c:forEach>
                </tr>
            </table>
        </form>
    </div>
    <div id="footBlack">
        <h4><br>Questions and suggestions? Contact us via our email:
            hotPizza@gmail.com</h4>
        <h4><br>Designed by MaksiDev</h4>
        <h5><br>License of 01.01.2018</h5>
    </div>
</div>
</body>
</html>