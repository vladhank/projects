<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>New client registration</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Font  -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <!-- Ajax	 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="assets/style/registration.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">

        <h2><spring:message code="registration.h2.text"/></h2>
        <p class="lead"><spring:message code="registration.p.text"/></p>
    </div>

    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 order-md-1">
            <h4 class="mb-3"><spring:message code="registration.h4.text"/></h4>
            <sec:authorize access="isAnonymous()">
            <s:form class="needs-validation" action="${pageContext.request.contextPath}/new_client"
                    method="POST" modelAttribute="client">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstName"><spring:message code="registration.first_name"/></label>
                    <s:input type="text" class="form-control" path="firstName" name="firstName" id="firstName"
                             placeholder="Ivan"
                             value=""/>
                    <div>
                        <s:errors path="firstName" cssStyle="color: red"/>
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="lastName"><spring:message code="registration.last_name"/></label>
                    <s:input type="text" class="form-control" path="lastName" name="lastName" id="lastName"
                             placeholder="Vasilevich" value=""/>
                    <div>
                        <s:errors path="lastName" cssStyle="color: red"/>
                    </div>
                </div>
            </div>

            <div class="mb-3">
                <label for="username"><spring:message code="registration.login"/></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">@</span>
                    </div>
                    <s:input type="text" class="form-control" name="login" path="login" id="username"
                             placeholder="you@example.com"/>
                    <div style="width: 100%;">
                        <s:errors path="login" cssStyle="color: red"/>
                        <c:set var="exception" value="${errorMessage}"/>
                        <p style="color:red">${exception}</p>
                    </div>
                </div>
            </div>

            <div class="mb-3">

                <label for="password"><spring:message code="registration.password"/></label>
                <s:input type="password" class="form-control" path="password" name="password" id="password"
                         onkeyup="check()"/>
                <div>
                <s:errors path="password" cssStyle="color: red"/>
            </div>
        </div>

        <div class="mb-3">
            <label for="confirmPassword"><spring:message code="registration.confirm_password"/></label>
            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                   onkeyup="check()">
            <span id='message'></span>
            <div class="invalid-feedback">
                <spring:message code="registration.invalid"/>
            </div>
        </div>

        <div class="col-2">
        </div>


        <div class="mb-3">
            <label for="phoneNumber"><spring:message code="registration.phone_number"/></label>
            <s:input type="text" class="form-control" path="phoneNumber" name="phoneNumber" id="phoneNumber"
                     placeholder="+375297777777" pattern="\+375\-[0-9]{2}\-[0-9]{7}"/>
            <div>
                <s:errors path="phoneNumber" cssStyle="color: red"/>
            </div>
        </div>

        <div class="row">

            <div class="col-md-3 mb-3">
                <label for="city"><spring:message code="registration.city"/></label>
                <input type="text" class="form-control" name="address.city" id="city" placeholder="Minsk">
            </div>
            <div class="col-md-3 mb-3">
                <label for="street"><spring:message code="registration.street"/></label>
                <input type="text" class="form-control" name="address.street" id="street"
                       placeholder="Skruganova">
            </div>
            <div class="col-md-3 mb-3">
                <label for="house"><spring:message code="registration.house"/></label>
                <input type="text" class="form-control" name="address.house" id="house" placeholder="16A">
            </div>
            <div class="col-md-3 mb-3">
                <label for="apartment"><spring:message code="registration.apartment"/></label>
                <input type="text" class="form-control" name="address.apartment" id="apartment"
                       placeholder="21">
            </div>
        </div>


        <div class="mb-3">
            <label for="dateOfBirth"><spring:message code="registration.date"/></label>
            <s:input type="date" class="form-control" path="dateOfBirth" name="dateOfBirth" id="dateOfBirth"
                     placeholder="1990-07-17"/>
            <div>
                <s:errors path="dateOfBirth" cssStyle="color: red"/>
            </div>
        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="registration.done_button"/></button>
            <%--<a class="btn btn-primary btn-lg btn-block" type="submit" href="../../frontController?command=newclient">I'am done</a>--%>
        </s:form>
        </sec:authorize>
    </div>
</div>

<footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2019 Vlad Hancharenka</p>
</footer>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>

<script src="assets/js/password_validation.js"></script>

<script src="assets/js/registration_checker.js"></script>
</body>
</html>
