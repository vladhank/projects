<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Payments</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="assets/style/main.css" rel="stylesheet">
</head>

<body class="text-center bg ">
<div class="cover-container d-flex h-100 p-3 mx-auto flex-column ">
    <header class="masthead mb-auto">
        <div class="inner">
            <h3 class="masthead-brand">Lightning Payments</h3>
            <nav class="nav nav-masthead justify-content-center">
                <a class="nav-link active" href="#"><spring:message code="main.home"/> </a>
                <a class="nav-link" href="#"><spring:message code="main.features"/></a>
                <a class="nav-link" href="#"><spring:message code="main.contacts"/></a>
                <c:url var="langEN" value="/main?lang=en"/>
                <a class="i18n" style="padding-left:1em"  href="${langEN}"><spring:message code="main.locale.en"/></a>
                <p class="i18n" style="padding: 3px">|</p>
                <c:url var="langRU" value="/main?lang=ru"/>
                <a class="i18n" href="${langRU}">
                    <spring:message code="main.locale.ru"/>
                </a>
            </nav>
        </div>
    </header>

    <main role="main" class="inner cover">
        <h1 class="cover-heading"><spring:message code="main.h1.text"/></h1>
        <p class="lead"><spring:message code="main.text"/></p>
        <p class="lead">
            <a href="<c:url value="/login"/>" class="btn btn-lg btn-secondary"><spring:message code="main.login"/></a>
        </p>
    </main>

    <footer class="mastfoot mt-auto">
        <div class="inner">
            <p>Vlad Hancharenka</p>
        </div>
    </footer>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="assets/js/vendor/jquery-slim.min.js"><\/script>')</script>

</body>
</html>
