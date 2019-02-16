<%--
  Created by IntelliJ IDEA.
  User: uladzislauhancharenka
  Date: 25/11/2018
  Time: 01:33
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Font  -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <!-- Ajax	 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- Custom styles for this template -->
    <link href="../../../assests/style/registration.css" rel="stylesheet">

</head>

<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">

        <h2>New client registration form</h2>
        <p class="lead">We welcome every new customer. Working with us is your pleasure.</p>
    </div>

    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 order-md-1">
            <h4 class="mb-3">Client info</h4>
            <form class="needs-validation" action="../../frontController?command=newclient" novalidate method="POST">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" name="firstName" id="firstName" placeholder="Ivan" value="" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" name="lastName"id="lastName" placeholder="Vasilevich" value="" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="username">Login (Email)</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">@</span>
                        </div>
                        <input type="text" class="form-control" name="login" id="username" placeholder="you@example.com" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            Your login is required.
                        </div>
                    </div>
                </div>

                <div class="mb-3">

                    <label for="password">Password </label>
                    <input type="password" class="form-control" name="password" id="password" onkeyup="check()">
                    <div class="invalid-feedback">
                        Please enter your password.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="confirmPassword">Confirm password </label>
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"  onkeyup="check()">
                    <span id='message'></span>
                    <div class="invalid-feedback">
                        Please enter your password one more time.
                    </div>
                </div>

                <div class="col-2">
                </div>



                <div class="mb-3">
                    <label for="phoneNumber">Phone number</label>
                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="+375297777777" pattern="\+375\-[0-9]{2}\-[0-9]{7}" required>
                    <div class="invalid-feedback">
                        Please enter your phone number.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" name ="address" id="address" placeholder="Minsk Skruganova 14-51" required>
                    <div class="invalid-feedback">
                        Please enter your address.
                    </div>
                </div>


                <div class="mb-3">
                    <label for="dateOfBirth">Date of birth</label>
                    <input type="text" class="form-control" name="dateOfBirth" id="dateOfBirth" placeholder="1990-07-17" required>
                    <div class="invalid-feedback">
                        When you was born?
                    </div>
                </div>


                <hr class="mb-4">

                <h4 class="mb-3">Payment info</h4>

                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                        <input id="cardCompany" name="cardCompany" value="VISA" type="radio" class="custom-control-input" checked required>
                        <label class="custom-control-label" for="cardCompany">VISA</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="MasterCard" name="cardCompany" value="MASTERCARD" type="radio" class="custom-control-input" required>
                        <label class="custom-control-label" for="MasterCard">MasterCard</label>
                    </div>

                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="firstName">First name on card</label>
                        <input type="text" class="form-control" name="cardFirstName" id="cardFirstName" placeholder="" required>
                        <small class="text-muted">Full first name as displayed on card</small>
                        <div class="invalid-feedback">
                            First name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="lastName">Last name on card</label>
                        <input type="text" class="form-control" name="cardLastName" id="cardLastName" placeholder="" required>
                        <small class="text-muted">Full last name as displayed on card</small>
                        <div class="invalid-feedback">
                            Last name on card is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cardNumber">Credit card number</label>
                        <input type="text" class="form-control" name="cardNumber" id="cardNumber" placeholder="4156890754320163" required>
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="pin">PIN</label>
                        <input type="password" class="form-control" name="pin" id="pin" placeholder="4 digits" minlength="3" maxlength="3" required>
                        <div class="invalid-feedback">
                            Pin is  required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">Expiration Date</label>
                        <input type="text" class="form-control" name="expDate" id="cc-expiration" placeholder="01/21" required>
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">CVV (3 digits)</label>
                        <input type="text" class="form-control" name="cvv"id="cc-cvv" placeholder="" minlength="4" maxlength="4" required>
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="status">Status</label>
                        <input type="text" class="form-control" name="status" id="status" placeholder="Active/Disabled" required>
                        <div class="invalid-feedback">
                            Status of your card is required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cashBack">Cashback(Optional)</label>
                        <input type="text" class="form-control" name="cashBack" id="cashBack" placeholder="5%">
                    </div>
                </div>
                <div class="row">
                    <div class="col mb-3">
                        <label for="bankAccount">Bank account (28 symbols)</label>
                        <input type="text" class="form-control" name="bankAccount" id="bankAccount" placeholder="" required>
                        <div class="invalid-feedback">
                            Bank account is required
                        </div>
                    </div>
                </div>

                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">I'am done</button>
                <%--<a class="btn btn-primary btn-lg btn-block" type="submit" href="../../frontController?command=newclient">I'am done</a>--%>

            </form>
        </div>
    </div>

    <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">&copy; 2018 Vlad Hancharenka</p>
    </footer>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>

<script src="../../../assests/js/password_validation.js"></script>

<script src="../../../assests/js/registration_checker.js"></script>
</body>
</html>
