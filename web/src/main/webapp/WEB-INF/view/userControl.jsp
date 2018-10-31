<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>user-Control</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="text-center">
    <h1>Rule the will of users!</h1>
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
                <th>Name Surname</th>
                <th>User Login</th>
                <th>User password</th>
                <th>Change Role</th>
                <th>Change Password</th>
                <th>Is blocked</th>
                <th>Delete</th>
                <th>Block</th>
                <th>Unblock</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${userDtoList}">
                <tr>
                    <th>${user.nameSurname}</th>
                    <th>${user.userLogin}</th>
                    <th>You can not see or use it :)</th>
                    <th>
                        <div class="btn-group">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                Set Role
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <c:forEach var="role" items="${userRole}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}
                                        /admin/user-control/set-new-role?userRole=${role.userRole}
                                        &userId=${user.id}">
                                                ${role.userRole}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}
                        /admin/user-control/set-new-password" method="post">
                            <input type="hidden" value="${user.id}" name="userId">
                            <input type="password" class="input-sm" name="password"
                                   placeholder="Password">
                            <input type="submit" class="btn btn-danger" value="Change">
                        </form>
                    </th>
                    <th>${user.blocked}</th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/user-control/delete-user"
                              method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="submit" class="btn btn-danger" value="Delete user!">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/user-control/block-user" method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="submit" class="btn btn-danger" value="Block user!">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/admin/user-control/unblock-user" method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="submit" class="btn btn-danger" value="Unblock user">
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
            case "deleteSuccess":
                out.print("<p align=\"center\"> Role was successfully changed </p>");
                break;
            case "passSuccess":
                out.print("<p align=\"center\"> Password was successfully changed </p>");
        }
    }
%>
</body>
</html>
