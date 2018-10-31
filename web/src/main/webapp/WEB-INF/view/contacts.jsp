<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <title>contacts</title>
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
        <h3>Our Contacts</h3>
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/user/news">Latest News</a></li>
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
    <h3>So, the only way to contact with us is via our email</h3>
    <h3>You just fulfill that form below and we will send an answer on your email</h3>
</div>

<div class="container-fluid">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
        <form:form role="form" action="/user/contacts/send-message" method="post"
                   modelAttribute="messageDto">
            <form:input path="userLogin" value="${userDto.userLogin}" type="hidden"/>
            <label for="messageTheme">Please type the theme of the message</label>
            <form:input path="messageTheme" name="messageTheme" id="messageTheme"
                        class="form-control" placeHolder="Message Theme"/>
            <label for="messageBody">Enter you message here</label>
            <form:textarea path="messageBody" name="messageBody" placeHolder="Message Body"
                           type="textarea" rows="5" class="form-control"
                           required="true" id="messageBody"/>
            <form:errors path="messageBody" value="You have not entered anything,
            or entererd too musch, size must not be longer than 250 symbols!"/>
            <input type="submit" class="btn btn-danger" value="Send" align="right">
        </form:form>
    </div>
    <div class="col-lg-3"></div>
</div>

<% String action = request.getParameter("action");
    if (action != null) {
        switch (action) {
            case "sentSuccess":
                out.print("<p align=\"center\"> Your message was successfully registered </p>");
                break;
            case "sentFailure":
                out.print("<p align=\"center\"> Error while sending message, try again </p>");
                break;
            case "":
                break;
        }
    }
%>

<div id="footBlack">
    <h4><br>Questions and suggestions? Contact us via our email:
        hotPizza@gmail.com</h4>
    <h4><br>Designed by MaksiDev</h4>
    <h5><br>License of 01.01.2018</h5>
</div>
</body>
</html>
