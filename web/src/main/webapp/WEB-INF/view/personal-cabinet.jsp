<%@ page import="com.maks.service.modelDto.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <title>personal cabinet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/personal-cabinet.js"></script>
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
        <h3><%out.print(userDto.getUserLogin());%></h3>
    </div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/user/news">
                        Latest News</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/contacts">
                        Contact us</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/user/main-page">
                        Main page</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Log out</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container-fluid">
    <p class="text-center, text-primary" align="center">
        <mark>Personal information</mark>
    </p>
    <table class="table table-borderless">
        <thead>
        <tr>
            <th>Name and Surname</th>
            <th>Login</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Password</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>${userDto.nameSurname}</th>
            <th>${userDto.userLogin}</th>
            <th>${userDto.address}</th>
            <th>${userDto.phone}</th>
            <th>****************</th>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <th>
                <div class="container-fluid">
                    <button class="btn btn-info" data-toggle="modal" data-target=
                            "#nameButton">Change name and Surname
                    </button>
                    <div id="nameButton" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4>You are going to change your name and surname</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/user/personal-cabinet/change-name-surname"
                                          method="post">
                                        <input type="hidden" value="${userDto.id}" name="userId">
                                        <input placeholder="Enter new name and surname"
                                               name="name" class="input-sm"
                                               onchange="validateFIO()" required>
                                        <input type="submit" class="btn btn-primary" value="Change">
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
            <th>Can not be changed</th>
            <th>
                <div class="container-fluid">
                    <button class="btn btn-info" data-toggle="modal" data-target=
                            "#addressButton">Change name and Surname
                    </button>
                    <div id="addressButton" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4>You are going to change your address</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/user/personal-cabinet/change-address"
                                          method="post">
                                        <input type="hidden" value="${userDto.id}" name="userId">
                                        <input placeholder="Enter new address"
                                               name="address" class="input-sm"
                                               onchange="validateStreet()" required>
                                        <input type="submit" class="btn btn-primary" value="Change">
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
            <th>
                <div class="container-fluid">
                    <button class="btn btn-info" data-toggle="modal" data-target=
                            "#phoneButton">Change phone
                    </button>
                    <div id="phoneButton" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4>You are going to change your phone</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/user/personal-cabinet/change-phone"
                                          name="passwordChange" method="post">
                                        <input type="hidden" value="${userDto.id}" name="userId">
                                        <input placeholder="Enter old password"
                                               name="phone" class="input-sm"
                                               onchange="validatePhone()" required>
                                        <input type="submit" class="btn btn-primary" value="Change">
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
            <th>
                <div class="container-fluid">
                    <button class="btn btn-info" data-toggle="modal" data-target=
                            "#myButton">Change password
                    </button>
                    <div id="myButton" class="modal fade">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4>You are going to change your password, be careful</h4>
                                    <h4>Please write it down</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="${pageContext.request.contextPath}/user/personal-cabinet/change-password"
                                          name="passwordChange" method="post">
                                        <input type="hidden" value="${userDto.id}" name="userId">
                                        <input placeholder="Enter old password"
                                               type="password" name="oldPassword" class="input-sm"
                                               onchange="validatePassword()" required>
                                        <input placeholder="Enter new password"
                                               type="password" name="newPassword" class="input-sm"
                                               onchange="validateNewPassword()" required>
                                        <input type="submit" class="btn btn-primary" value="Change">
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
        </tfoot>
    </table>
</div>

<% String change = request.getParameter("change");
    if (change != null) {
        switch (change) {
            case "passSuccess":
                out.print("<p align=\"center\">You've successfully changed your password, " +
                        "we recommend you to log out and log in </p>");
                break;
            case "passFailure":
                out.print("<p align=\"center\"> Error while changing password, please try again </p>");
                break;
            case "nameSuccess":
                out.print("<p align=\"center\"> Name and Surname were changed </p>");
                break;
            case "nameFailure":
                out.print("<p align=\"center\"> Error while changing name and surname, try again </p>");
                break;
            case "addressTrue":
                out.print("<p align=\"center\"> Address was changed </p>");
                break;
            case "addressFalse":
                out.print("<p align=\"center\"> Error while changing address, try again </p>");
                break;
            case "phoneSuccess":
                out.print("<p align=\"center\"> Phone was changed </p>");
                break;
            case "phoneFailure":
                out.print("<p align=\"center\"> Error while changing phone, try again </p>");
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
