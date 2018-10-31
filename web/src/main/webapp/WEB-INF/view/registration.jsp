<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/scripts/registrationScripts.js"></script>
    <style>
        .error {
            color: red;
            font-size: 0.9em;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="col-lg-4"></div>
<div class="col-lg-6">
    <h1>Registration Page</h1>
    <form:form name="register" action="${pageContext.request.contextPath}/registration/add-new-user"
               modelAttribute="userDto" method="post">
        <p>
                <form:input path="nameSurname" name="nameSurname" placeholder="Name Surname"
                            onchange="validateFIO()" cssClass="input-sm"
                            required="true"/> Like this: John Smith
                <form:errors path="nameSurname" cssClass="error"/>
        <p>
                <form:input path="userLogin" name="email" placeholder="E-m@il"
                            onchange="validateEmail()" cssClass="input-sm" required="true"/>
            Example: blabla-bla@gmail.com - (Your login in future)
                <form:errors path="userLogin" cssClass="error"/>
        <p>
                <form:input path="phone" name="phone" placeholder="Phone number"
                            onchange="validatePhone()" cssClass="input-sm" required="true"/>
            Example: +375(29/44/25/17) 1111111
                <form:errors path="phone" cssClass="error"/>
        <p>
                <form:input path="address" name="address" placeholder="Address"
                            onchange="validateStreet()" cssClass="input-sm" required="true"/>
            Example: Pushkinskaya 22-11 Street
                <form:errors path="address" cssClass="error"/>
        <p>
                <form:input path="password" name="password" type="password"
                            placeholder="Password" onchange="validatePassword()"
                            cssClass="input-sm" required="true"/>
            Example: ******** (Min 5 <-> Max 20 symbols)
                <form:errors path="password" cssClass="error"/>
        <p><input name="repeatedPassword" type="password"
                  placeholder="Repeat password" class="input-sm"
                  onchange="validateFirstAndSecondPassword()" required/>
            Example: ******** (Min 5 <-> Max 20 symbols)

        <p><input type="submit" class="btn btn-danger" value="Sign up"></p>
    </form:form>
</div>
<div class="col-lg-2"></div>
<a href="${pageContext.request.contextPath}/">Login page here</a>
</body>
</html>