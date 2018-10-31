<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/indexScripts.js"></script>
</head>
<body>
<div class="col-lg-4"></div>
<div class="col-lg-4">
    <h1>Welcome</h1>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input name="username" id="username" placeholder="Login" class="input-sm"
               required onchange="return validateLengthLogin()">
        <input name="password" id="password" placeholder="Password" class="input-sm"
               type="password"
               required onchange="return validateLengthPassword()">
        <input type="submit" class="btn btn-primary" value="Sign in">
    </form>
    <form action="${pageContext.request.contextPath}/registration" method="get">
        <input type="submit" class="btn btn-danger" value="Sign up">
    </form>
    <form action="${pageContext.request.contextPath}/restore-password" method="get">
        <input type="submit" class="btn btn-danger" value="Forgot password">
    </form>
    <% String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "loginError":
                    out.print("<p align=\"center\"> Wrong Login or Password, please try again </p>");
                    break;
                case "addingSuccess":
                    out.print("<p align=\"center\"> You've been registered," +
                            " please check email for confirmation letter! </p>");
                case "":
                    break;
            }
        }
    %>
    <%
        String account = request.getParameter("account");
        if (account != null) {
            switch (account) {
                case "blockedNotActivated":
                    out.print("<p align=\"center\"> Account was blocked, email us to know the reason." +
                            " Or account is not confirmed </p>");
                    break;
                case "null":
                    out.print("<p align=\"center\"> Link is not working, try to sign up again </p>");
                    break;
                case "confirmed":
                    out.print("<p align=\"center\"> Account confirmed, you may sign in now </p>");
                    break;
                case "notConfirmed":
                    out.print("<p align=\"center\"> Account is not confirmed, try to sign up again </p>");
                    break;
            }
        }
    %>
    <% if (request.getParameter("logout") != null) {
        out.print("<p align=\"center\"> You've been logged out successfully </p>");
    }
    %>
    <%
        String reset = request.getParameter("reset");
        if (reset != null) {
            switch (reset) {
                case "true":
                    out.print("<p align=\"center\"> New password was sent to your email </p>");
                    break;
                case "false":
                    out.print("<p align=\"center\"> Something went wrong, try again please </p>");
                    break;
            }
        }
    %>
</div>
<div class="col-lg-4"></div>
</body>
</html>