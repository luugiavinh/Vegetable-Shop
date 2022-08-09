<%-- 
    Document   : confirm
    Created on : Mar 6, 2022, 9:42:08 AM
    Author     : admin
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Confirm Purchase Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="styleconfirm.css" rel="stylesheet">
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            String message= (String) request.getAttribute("MESSAGE");
            if(message==null){
                message="";
            }
            
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

                                <li ><a href="MainController?action=ViewCart"><span class="glyphicon glyphicon-credit-card"> </span> Cart</a></li>
                                <li ><a href="MainController?action=SearchProduct&search="><span class="glyphicon glyphicon-shopping-cart"></span> Shopping</a></li>
                                <li><a href="MainController?action=UserView"><span class="glyphicon glyphicon-user"></span> User</a></li>

                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>

            </div>                        

        </div> <!--Kết thúc container-fluid-->
        
        <h2><%= message %></h2>
        
        <div class="row" id="servicetitle">

                <div class="col-md-12">

                    <img style="width: 50%" src="images/welcome1.jpg" />

                </div>

        </div>
        <div class="background">
        <h1>Confirm your cart</h1>
        
        <%
            Cart cart= (Cart) session.getAttribute("CART");
            if(cart!=null){
                if(cart.getCart().size()>0){
        %>
        
                <table style="width: 60%; margin-left: 300px " border="1">
                    <thead>
                        <tr>
                            <th>NO</th>
                            <th>Product ID</th>
                            <th style="width: 20%">Product Name</th>
                            <th>Import Date</th>
                            <th>Using Date</th>
                            <th>Price</th>
                            <th style="width: 1%">Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count=1;
                            double total=0;
                            for(ProductDTO product : cart.getCart().values()){
                                total+= product.getPrice()* product.getQuantity();
                        %>
                        <form action="MainController">
                    
                        <tr>
                            <td><%= count++ %></td>
                            <td><%= product.getProductID()%></td>
                            <td><%= product.getProductName()%></td>
                            <td><%= product.getImportDate() %></td>
                            <td><%= product.getUsingDate() %></td>
                            <td><%= product.getPrice()%>đ</td>                          
                            <td><%= product.getQuantity() %>kg</td>
                            <td><%= product.getPrice() * product.getQuantity() %>đ</td>                            
                        </tr>
                    </form>    
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <h1>Total: <span style="font-weight: bolder"><%= total %>đ</span></h1>
                <a style="margin-left: 640px; font-size: 26px; font-family: cursive; font-style: oblique; color: #50cf5b; font-weight: bold" href="MainController?action=Confirm">Confirm Purchase</a></br>
        <%
                }
            }
        %>
        <a style="margin-left: 670px; font-size: 26px; font-family: cursive; font-style: oblique; color: #50cf5b; font-weight: bold"href="MainController?action=ViewCart">Back to Cart</a>        
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
    </body>

</html>
