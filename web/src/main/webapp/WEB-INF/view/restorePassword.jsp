<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>restore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <form action="${pageContext.request.contextPath}/restore-password/send-new" method="post">
            <input name="email" class="input-sm" title="Your email" placeholder="Your email" required>
            <input type="submit" class="btn btn-danger" value="Send">
        </form>
    </div>
    <div class="col-lg-4"></div>
</div>
</body>
</html>
