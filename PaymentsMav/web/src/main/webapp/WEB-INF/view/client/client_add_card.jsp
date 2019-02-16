<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Add new card </title>

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

        <h2><spring:message code="add_card.h2.text"/></h2>
        <p class="lead"><spring:message code="add_card.greeting.text"/></p>
    </div>

    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 order-md-1">
            <h4 class="mb-3"><spring:message code="add_card.client_info"/></h4>
            <sec:authorize access="isAuthenticated()">
                <s:form class="needs-validation" action="${pageContext.request.contextPath}/add_credit_card"
                        method="POST" modelAttribute="creditCard">

                    <hr class="mb-4">

                    <h4 class="mb-3"><spring:message code="add_card.h4.text"/></h4>

                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="cardCompany" name="cardCompany" value="VISA" type="radio"
                                   class="custom-control-input" checked required>
                            <label class="custom-control-label" for="cardCompany">VISA</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="MasterCard" name="cardCompany" value="MASTERCARD" type="radio"
                                   class="custom-control-input" required>
                            <label class="custom-control-label" for="MasterCard">MasterCard</label>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName"><spring:message code="add_card.first_name"/></label>
                            <s:input type="text" class="form-control" path="firstName" name="firstName" id="firstName"
                                     placeholder=""/>
                            <small class="text-muted"><spring:message code="add_card.description.first_name"/></small>
                            <div>
                                <s:errors path="firstName" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName"><spring:message code="add_card.last_name"/></label>
                            <s:input type="text" class="form-control" path="lastName" name="lastName" id="lastName"
                                     placeholder=""
                            />
                            <small class="text-muted"><spring:message code="add_card.description.last_name"/></small>
                            <div>
                                <s:errors path="lastName" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cardNumber"><spring:message code="add_card.card_number"/></label>
                            <s:input type="text" class="form-control" path="cardNumber" name="cardNumber"
                                     id="cardNumber"
                                     placeholder="4156890754320163"/>
                            <div>
                                <s:errors path="cardNumber" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="pin">PIN</label>
                            <s:input type="password" class="form-control" path="pin" name="pin" id="pin"
                                     placeholder="4 digits"
                                     minlength="4" maxlength="4"/>
                            <div>
                                <s:errors path="pin" cssStyle="color: red"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-3 mb-3">
                            <label for="expDate"><spring:message code="add_card.exp_dat"/></label>
                            <s:input type="date" class="form-control" path="expDate" name="expDate" id="expDate"
                                     placeholder="01/21"/>
                            <div>
                                <s:errors path="expDate" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cvv"><spring:message code="add_card.cvv"/></label>
                            <s:input type="text" class="form-control" path="cvv" name="cvv" id="cvv" placeholder=""
                                     minlength="3"
                                     maxlength="3"/>
                            <div>
                                <s:errors path="cvv" cssStyle="color: red"/>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="status"><spring:message code="add_card.status"/></label>
                            <input type="text" class="form-control" name="status" id="status"
                                   placeholder="Active/Disabled" required>
                            <div class="invalid-feedback">
                                <spring:message code="add_card.status.invalid"/>
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="cashBack"><spring:message code="add_card.cashback"/></label>
                            <input type="text" class="form-control" name="cashBack" id="cashBack" placeholder="5%">
                        </div>
                    </div>
                    <hr class="mb-4">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Add card</button>

                </s:form>
            </sec:authorize>
        </div>
    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2018 Vlad Hancharenka</p>
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
