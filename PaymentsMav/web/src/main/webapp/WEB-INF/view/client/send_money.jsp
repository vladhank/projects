<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Send money</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Font  -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <!-- Ajax  -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="assets/style/registration.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">

        <h2><spring:message code="send_money.h2.text"/></h2>
        <p class="lead"><spring:message code="send_money.p.text"/></p>
    </div>

    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 order-md-1">

            <form class="needs-validation" action="${pageContext.request.contextPath}/send_money" novalidate method="POST">


                <div class="col-2">
                </div>

                <h4 class="mb-3 text-center"><spring:message code="send_money.h4.text"/></h4>

                <div class="d-block my-3">


                </div>
                <div class="col">
                    <p class="text-center text-success">
                        <spring:message code="send_money.balance"/> ${balance}$
                    </p>
                </div>
                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label for="firstName"><spring:message code="send_money.name"/></label>
                        <input type="text" class="form-control" name="cardFirstName" id="firstName" placeholder=""
                               required>
                        <small class="text-muted"><spring:message code="send_money.name_description"/></small>
                        <div class="invalid-feedback">
                            <spring:message code="send_money.invalid_name"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName"><spring:message code="send_money.last_name"/></label>
                        <input type="text" class="form-control" name="cardLastName" id="lastName" placeholder=""
                               required>
                        <small class="text-muted"><spring:message code="send_money.last_name_description"/></small>
                        <div class="invalid-feedback">
                            <spring:message code="send_money.invalid_last_name"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cardNumber"><spring:message code="send_money.card_number"/></label>
                        <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                               placeholder="4156890754320163" onkeyup="cardCompany()" required>
                        <span id='message'></span>
                        <div class="invalid-feedback">
                            <spring:message code="send_money.card_number.invalid"/>
                        </div>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="moneyAmount"><spring:message code="send_money.amount"/></label>
                        <input type="text" class="form-control" name="moneyAmount" id="moneyAmount" placeholder="425$"
                               required>
                        <div class="invalid-feedback">
                            <spring:message code="send_money.amount.invalid"/>
                        </div>

                    </div>
                </div>
                <div class="row">

                    <div class="col">
                        <p hidden class="text-center transaction"><spring:message code="send_money.not_enough"/></p>
                    </div>
                </div>


                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit"><spring:message code="button.send"/></button>
            </form>
        </div>
    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p id="moneyMessage" class="mb-1">&copy; 2019 Vlad Hancharenka</p>
    </footer>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>

<script src="assets/js/card_type.js"></script>


<script src="assets/js/registration_checker.js"></script>
</body>
</html>

