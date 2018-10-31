<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>messenger</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container text-center">
    <h3>Reply user's messages!</h3>
</div>
<div class="container">
    <div class="table">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Sender</th>
                <th>Theme</th>
                <th>Body</th>
                <th>Reply</th>
                <th>Send reply</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="message">
                <tr>
                    <th>${message.userLogin}</th>
                    <th>${message.messageTheme}</th>
                    <th>${message.messageBody}</th>
                    <th>
                        <form:form action="${pageContext.request.contextPath}/admin/message-control/reply"
                                   id="replyForm" method="post" modelAttribute="replyObject">
                            <form:input path="userId" type="hidden" name="userId" value="${message.userId}"/>
                            <form:input path="userMessageId" type="hidden" name="messageId"
                                        value="${message.messageId}"/>
                            <form:input path="userLogin" type="hidden" name="userLogin" value="${message.userLogin}"/>
                            <form:input path="replyBody" class="input-lg" name="reply" placeholder="Your reply"
                                        required="true"/>
                            <form:errors path="replyBody"/>
                        </form:form>
                    </th>
                    <th>
                        <input type="submit" class="btn btn-danger" value="Send reply" form="replyForm">
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%
    String reply = request.getParameter("reply");
    if (reply != null) {
        switch (reply) {
            case "true":
                out.print("<p align=\"center\"> Message successfully sent </p>");
        }
    }
%>
</body>
</html>
