<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>denied</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container text-center">
    <h1>Dear user you are</h1>
    <h1>not allowed to see or use this page!</h1>
    <h3><a href="${pageContext.request.contextPath}/logout">CLICK HERE TO LOG OUT!</a></h3>
</div>
</body>
</html>
