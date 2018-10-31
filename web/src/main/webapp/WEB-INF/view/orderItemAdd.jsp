<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>order-item control</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container text-center">
    <h1>Online pizza store</h1>
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
<div class="text-center">
    <h3>Add new items here</h3>
</div>

<%--HAVE TO ADD NEW SPRING FORM THAT CONTANS ALL FIELDS FOR NEW ORDER ITEM
AND ALSO ADD NEW ANNOTATIONS TO RESTRICT SOME USE, ALSO USE MULTIPART FILE!--%>
<form:form modelAttribute="uploadOrderItem" method="post"
           action="/admin/order-item/add-new" enctype="multipart/form-data"
           id="formForUploadOrderItem">
    <div class="container">
        <div class="table">
            <table class="table">
                <thead>
                <tr>
                    <th>Order Item name</th>
                    <th>Order Item description</th>
                    <th>Order Item picture name</th>
                    <th>Order Item price</th>
                    <th>Upload picture</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th><form:input path="orderItemName" cssClass="input-sm"
                                    required="true" placeholder="Name"/>
                        <form:errors path="orderItemName"/></th>
                    <th><form:input path="orderItemDescription" cssClass="input-sm"
                                    required="true" placeholder="Description"/>
                        <form:errors path="orderItemDescription"/></th>
                    <th><form:input path="orderItemPictureName" cssClass="input-sm"
                                    required="true" placeholder="Picture name"/>
                        <form:errors path="orderItemPictureName"/></th>
                    <th><form:input path="orderItemPrice" cssClass="input-sm"
                                    required="true" placeholder="Price"/>
                        <form:errors path="orderItemPrice"/></th>
                    <th><input type="file" name="file">
                        <input type="submit" class="btn btn-danger" value="Upload"></th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form:form>

<div class="container">
    <div class="table">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Copy</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderItemDtoList}" var="orderDto">
                <tr>
                    <th>${orderDto.orderItemName}</th>
                    <th>${orderDto.orderItemDescription}</th>
                    <th>${orderDto.orderItemPrice}</th>
                    <th>
                        <form>
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/order-item/copy"
                              method="post">
                            <input type="hidden" value="${orderDto.orderItemId}" name="orderId">
                            <input type="submit" class="btn btn-info" value="Copy">
                        </form>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%
    String action = request.getParameter("action");
    if (action != null) {
        switch (action) {
            case "true":
                out.println("<p align=\"center\">You've successfully uploaded new Order Item </p>\"");
                break;
            case "false":
                out.println("<p align=\"center\">Something went wrong, try again please </p>\"");
                break;
            case "copyTrue":
                out.print("<p align=\"center\">Order Item was successfully copied</p>\"");
                break;
            case "copyFalse":
                out.print("<p align=\"center\">Something went wrong, try again please </p>\"");
        }
    }
%>
</body>
</html>
