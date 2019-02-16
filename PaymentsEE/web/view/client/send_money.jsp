<%@ page import="entities.Client" %>
<%@ page import="sun.misc.Cleaner" %>
<%@ page import="services.ClientService" %>
<%@ page import="services.Impl.ClientServiceImpl" %>
<%@ page import="entities.CreditCard" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.Bank" %>
<%@ page import="services.CreditCardService" %>
<%@ page import="services.Impl.CreditCardServiceImpl" %>
<%@ page import="services.BankService" %>
<%@ page import="services.Impl.BankServiceImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="style/registration.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">

        <h2>Who will get your money?</h2>
        <p class="lead">Please provide information about receiver of your paymnet</p>
    </div>

    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 order-md-1">

            <form class="needs-validation" novalidate method="POST">


                <div class="col-2">
                </div>

                <h4 class="mb-3 text-center">Receiver info</h4>

                <div class="d-block my-3">


                </div>
                <%
                    ClientService clientService = ClientServiceImpl.getInstance();
                    CreditCardService creditCardService = CreditCardServiceImpl.getInstance();
                    BankService bankService = BankServiceImpl.getInstance();
                    Client client = (Client) request.getSession().getAttribute("client");
                    List<CreditCard> cards = creditCardService.getCardsByID(client.getClientID());
                    Bank bank = bankService.get(cards.get(0).getCardNumber());
                %>
                <div class="col">
                    <p class="text-center text-success">
                        Available balance <%=bank.getBalance()%>
                    </p>
                </div>
                <div class="row">

                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name on card</label>
                        <input type="text" class="form-control" name="cardFirstName" id="firstName" placeholder=""
                               required>
                        <small class="text-muted">Full first name as displayed on card</small>
                        <div class="invalid-feedback">
                            First name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name on card</label>
                        <input type="text" class="form-control" name="cardLastName" id="lastName" placeholder=""
                               required>
                        <small class="text-muted">Full last name as displayed on card</small>
                        <div class="invalid-feedback">
                            Last name on card is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cardNumber">Credit card number</label>
                        <input type="text" class="form-control" name="cardNumber" id="cardNumber"
                               placeholder="4156890754320163" onkeyup="cardCompany()" required>
                        <span id='message'></span>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="moneyAmount">Amount of money</label>
                        <input type="text" class="form-control" name="moneyAmount" id="moneyAmount" placeholder="425$"
                               required>
                        <div class="invalid-feedback">
                            Amount of money is required for transaction
                        </div>
                    </div>
                </div>
                <div class="row">

                    <div class="col">
                        <p hidden class="text-center transaction">Not enought money</p>
                    </div>
                </div>


                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">I'am done</button>
            </form>
        </div>
    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p id="moneyMessage" class="mb-1">&copy; 2018 Vlad Hancharenka</p>
    </footer>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>

<script src="../../../assests/js/card_type.js"></script>


<script src="../../../assests/js/registration_checker.js"></script>
</body>
</html>

