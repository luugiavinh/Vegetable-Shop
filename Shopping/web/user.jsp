<%-- 
    Document   : user
    Created on : Feb 28, 2022, 2:06:59 PM
    Author     : admin
--%>

<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="styleuser.css" rel="stylesheet">
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String userID = loginUser.getUserID();
            String fullName = loginUser.getFullName();
            String roleID = loginUser.getRoleID();
            String address = loginUser.getAddress();
            String birthday = loginUser.getBirthday();
            String phone = loginUser.getPhone();
            String email = loginUser.getEmail();
        %>

        <div class="container-fluid">

            <div class="row">

                <nav class="navbar navbar-inverse bg-primary"  role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>

                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">

                                <li class="active"> <a href="user.jsp"> <span class="glyphicon glyphicon-home"></span> index</a> </li>

                                <li ><a href="MainController?action=ViewCart"><span class="glyphicon glyphicon-credit-card"></span> Cart</a></li>
                                <li ><a href="MainController?action=SearchProduct&search="><span class="glyphicon glyphicon-shopping-cart"></span> Shopping</a></li>
                                <li><a href="MainController?action=UserView"><span class="glyphicon glyphicon-user"></span> User</a></li>

                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>

            </div>



        </div> <!--Kết thúc container-fluid-->

        <div class="container" id="servicetitle">

            <div class="row">

                <div class="col-md-12">

                    <h1>WELCOME <%= loginUser.getFullName()%> TO OUR SHOP</h1>

                </div>

            </div>

            <div class="row">

                <div class="col-md-12">

                    <img style="width: 50%" src="images/welcome1.jpg" />

                </div>

            </div>
                    
            <div class="userbackground">
                <h1 >User Information</h1>
                <div class="user">
                    User ID: <%= userID%></br>
                    Full Name: <%= fullName%></br>
                    Role ID: <%= roleID%></br>
                    Address: <%= address%></br>
                    Birthday: <%= birthday%></br>
                    Phone: <%= phone%></br>
                    Email: <%= email%></br>
                    Password: ***
                </div>
            
                    
                <form action="MainController">
                    <input type="submit" name="action" value="Logout" />
                </form>
            
            </div>

            <div class="container-fluid" id="lastchild">

                <div class="row contactinfo">

                    <div>

                        <p>Email : VegetableShop@gmail.com</p>
                        <p>Address: 100 Vuon Lai, Tan Phu District, HCMC</p>
                        <h5>&copy; Copyright 2021. VegetableShop</h5>
                    </div>

                </div>

            </div>
        </div>
        

    </body>
</html>
