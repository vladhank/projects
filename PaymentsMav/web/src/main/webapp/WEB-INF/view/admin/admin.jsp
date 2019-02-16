<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Admin_Clients</title>

    <!-- Bootstrap CSS CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <!--Custom CSS -->
    <link rel="stylesheet" href="assets/style/dashboard.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <!-- Font Awesome JS -->
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
            integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
            crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
            integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
            crossorigin="anonymous"></script>
    <!-- Icons -->
    <script src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
</head>

<body>
<div class="wrapper">
    <!-- Sidebar  -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>Lightning Payments</h3>
        </div>

        <ul class="list-unstyled components">
            <li class="active">
                <a href="<c:url value="${pageContext.request.contextPath}/admin"/>"><spring:message code="admin.clients"/></a>
            </li>
            <li>
                <a href="<c:url value="${pageContext.request.contextPath}/cards"/>"><spring:message code="admin.cards"/></a>
            </li>

            <li>
                <a href="<c:url value="${pageContext.request.contextPath}/transactions"/>"><spring:message code="admin.transactions"/></a>
            </li>
            <li>
                <a href="<c:url value="${pageContext.request.contextPath}/bank_accounts"/>"><spring:message code="admin.bank_accounts"/></a>
            </li>
        </ul>


    </nav>

    <!-- Page Content  -->
    <div id="content">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span><spring:message code="button.roll_up"/></span>
                </button>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fas fa-align-justify"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">

                        <li class="nav-item">

                            <a class="nav-link" href="<c:url value="/logout" />" data-toggle="modal" data-target="#logoutModal">
                                <!-- <img src="feather/log-out.svg" alt=""> -->
                                <!-- <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"></a> -->
                                <spring:message code="button.logout"/></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <h2><spring:message code="admin.clients.h2.text"/></h2>
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
        <div class="col-12 text-center">
            <a href="view/signin/registration.jsp">
                <button class="btn btn-primary btn-lg " type="submit"><spring:message code="button.add_client"/></button>
            </a>
        </div>
        <div>
            <p></p>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-table"></i>
                <spring:message code="admin.clients_table"/>
            </div>
            <form action="frontController" novalidate method="POST">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>ClientID</th>
                                <th>First_Name</th>
                                <th>Last_Name</th>
                                <th>Phone_Number</th>
                                <th>Address</th>
                                <th>DateOfBirth</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>ClientID</th>
                                <th>First_Name</th>
                                <th>Last_Name</th>
                                <th>Phone_Number</th>
                                <th>Address</th>
                                <th>DateOfBirth</th>
                                <th>Action</th>
                            </tr>
                            </tfoot>
                            <c:forEach var="client" items="${clients}" varStatus="status">
                                <tr>
                                    <td>${client.clientID}</td>
                                    <td>${client.firstName}</td>
                                    <td>${client.lastName}</td>
                                    <td>${client.phoneNumber}</td>
                                    <td>${client.address}</td>
                                    <td>${client.dateOfBirth}</td>
                                    <td>
                                        <form action="../../frontController?command=delete" method="POST">
                                        <%--<a class="delete btn btn-primary" href="../../frontController?command=delete"--%>
                                           <%--name="clientid" id="${client.clientID}">Delete</a>--%>
                                            <button id="delete" class="btn btn-primary" name="clientid" value="${client.clientID}" type="submit">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </form>
            <!-- Logout Modal-->
            <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel"><spring:message code="button.logout.header"/></h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body"><spring:message code="button.logout.text"/>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal"><spring:message
                                    code="button.cancel"/></button>
                            <a class="btn btn-primary" href="<c:url value="/logout" />"><spring:message code="button.logout"/></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-footer small text-muted"><spring:message code="admin.update"/></div>
        </div>
        <p class="small text-center text-muted my-5">
            <em>Powered by Vlad Hancharenka</em>
        </p>
    </div>
</div>


</div>

<script>
    $(document).ready(function () {
        $("#delete").click(function (e) {
            e.preventDefault();
            var id = +this.id;
            $.ajax({
                url: "../../frontController?command=delete",
                type: "POST",
                data: {
                    id: id,
                },
                success: function (data) {
                    alert(data); // alerts the response from jsp
                    location.reload();
                }
            });
        });
    });
</script>

<!-- jQuery CDN - Slim version (=without AJAX) -->

<!-- Popper.JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>

<script type="text/javascript" src="assets/js/sidebar.js"></script>
<%--<script type="text/javascript" src="../../../assests/js/pagination.js"></script>--%>

</body>

</html>