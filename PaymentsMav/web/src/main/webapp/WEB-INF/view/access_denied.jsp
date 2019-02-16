<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <META http-equiv="refresh" content="4;URL=<c:url var="loginUrl" value="/login"/>">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Access Denied</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Font  -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script>
        var timeout = 5; //set time here 5sec
        function timer() {
            if (--timeout > 0) {
                document.forma.clock.value = timeout;
                window.setTimeout("timer()", 1000);
            }
            else {
                document.forma.clock.value = "Time over";
                ///disable submit-button etc
            }
            if (document.forma.clock.value == "Time over") {
                top.document.location.replace('/login'); //redirect page url
            }
        }
    </script>
    <!-- Custom styles for this template -->
    <link href="assets/style/signin.css" rel="stylesheet">
</head>

<body>


<div class="container">
    <div class="row justify-content-md-center">
        <h4 class="text-center" style="color: #FF6F61">We could not find account with this login or password</h4>
    </div>
    <div class="row justify-content-md-center">
        <form  name="forma">
            <% String clock = null; %>
            You will be redirected to login page after:<input type="text" size='1' name="clock" value="<%=clock%>" style="border:1px solid white">seconds
            <script>
                timer(); //calljs timer()
            </script>
        </form>
    </div>
</div>

</body>
</html>
