<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Signin</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Font  -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>

    <!-- Custom styles for this template -->
    <link href="assets/style/signin.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-signin" method="POST">
    <img class="mb-4" src="assets/images/lightning.jpg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal " style="color: #7386D5"><spring:message code="login.h1"/></h1>
    <%--<c:url var="loginUrl" value="/login"/>--%>
    <%--<form action="${loginUrl}" method="post" action="/mvc/j_spring_security_check">--%>
    <form method="post" action="/login">
        <label for="inputLogin" class="sr-only"><spring:message code="login.login"/></label>
        <input type="login" name="login" id="inputLogin" class="form-control" placeholder="Login" required autofocus>
        <label for="inputPassword" class="sr-only"><spring:message code="login.password"/></label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" name="remember-me" value="remember-me"><spring:message code="login.remember_me"/>
            </label>
            <br>
            <a href="<c:url value="/registration"/>"><spring:message code="login.registration"/></a>
        </div>
        <button class="btn btn-lg btn-color btn-block" type="submit"><spring:message code="login.sign_in"/></button>
        <!-- #7386D5 -->
    </form>
</form>
</body>
</html>
