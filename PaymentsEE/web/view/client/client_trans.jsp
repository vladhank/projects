<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Client_transaction</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="../../../assests/style/dashboard.css">

    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
            integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
            crossorigin="anonymous"></script>
    <!-- icons  -->
    <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
</head>

<body>
<div class="wrapper">
    <!-- Sidebar  -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Lightning Paymnets</h3>
        </div>

        <ul class="list-unstyled components">
            <li>
                <a href="../../frontController?command=client">My info</a>

            </li>
            <li class="active">
                <a href="../../frontController?command=clienttrans">My transactions</a>
            </li>

            <li>
                <a href="../../frontController?command=clientcard">My Cards</a>
            </li>
            <li>
                <a href="../../frontController?command=clientbank">My bank account</a>
            </li>
        </ul>


    </nav>

    <!-- Page Content  -->
    <div id="content">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span>Roll up</span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">
                        <li class="nav-item">
                            <a href="send_money.jsp">
                                <button type="button" class="btn btn-primary btn-space">Send money</button>
                            </a>
                        </li>
                        <li class="nav-item">
                            <button type="button" class="btn btn-info ">Download info</button>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="modal" data-target="#logoutModal">
                                <!-- <img src="feather/log-out.svg" alt=""> -->
                                <!-- <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"></a> -->
                                Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <h2>My info </h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
            nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
            anim id est laborum.</p>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
            dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
            ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
            nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
            anim id est laborum.</p>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                All about me
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>TransactionID</th>
                            <th>Card number</th>
                            <th>Bank accountID</th>
                            <th>Transaction type</th>
                            <th>Amount of money</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th>TransactionID</th>
                            <th>Card number</th>
                            <th>Bank accountID</th>
                            <th>Transaction type</th>
                            <th>Amount of money</th>
                        </tr>
                        </tfoot>
                        <c:forEach var="transaction" items="${transactions}" varStatus="status">
                            <c:choose>
                                <c:when test="${transaction.transactionType=='WITHDRAW'}">
                                    <tr class="alert alert-danger">
                                </c:when>
                                <c:when test="${transaction.transactionType=='DEPOSIT'}">
                                    <tr class="alert alert-success">
                                </c:when>
                            </c:choose>
                            <td>${transaction.transactionID}</td>
                            <td>${transaction.cardNumber}</td>
                            <td>${transaction.accountID}</td>
                            <td>${transaction.transactionType}</td>
                            <td>${transaction.amountMoney}$</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="../../frontController?command=logout">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer small text-muted">Everything will be fine</div>
        </div>
        <p class="small text-center text-muted my-5">
            <em>Powered by Vlad Hancharenka</em>
        </p>
    </div>
</div>


</div>

<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>

<script type="text/javascript" src="../../../assests/js/sidebar.js"></script>
<script type="text/javascript" src="../../../assests/js/pagination.js"></script>

</body>

</html>