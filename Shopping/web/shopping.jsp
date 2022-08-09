<%-- 
    Document   : shopping
    Created on : Mar 1, 2022, 1:05:47 PM
    Author     : admin
--%>

<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Shopping Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="stylehome.css" rel="stylesheet">
    </head>
    <body>
        
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            String search = request.getParameter("search");
            if(search==null){
                search="";
            }
            
            String message = (String) request.getAttribute("MESSAGE");
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


                            <form class="navbar-form navbar-right"  action="MainController" method="GET">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search" name="search" value="<%= search %>"/>
                                </div>
                                <input style="color: black" type="submit" name="action" value="SearchProduct"/>
                            </form>

                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>

            </div>                        

        </div> <!--Kết thúc container-fluid-->

        <div class="container" id="servicetitle">
            <h1><%= message %></h1>
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
		
            <div class="row promotion" >

                <div class="col-md-12">

                    <h2>Our Product</h2>
                    <p>A healthy diet is a solution to many of our health problems</p>
                </div>

            </div>

            <div class="row sanpham">
                <%
                    List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
                    if (listProduct != null) {
                        if (listProduct.size() > 0) {
                            for (ProductDTO product : listProduct) {

                %>

                <div class="col-md-4"  style="height: 570px">

                    <div class="combo">

                        <img style="width: 480px; height: 300px" src="<%= product.getImage()%>" />
                        <p style="text-align: center; font-size: 24px; font-weight: bold"  class="combodetail"><%= product.getProductName()%><span style="margin-left: 20px">( <%= (int) product.getPrice() %>đ/kg )</span></p>
                        <p>Quantity left:<span style="font-weight: bold;margin-left: 10px; font-size: 20px"><%= product.getQuantity()%>kg</span> </p>
                        <p>Category: <span style="font-weight: bold"><%= product.getCategoryID()%></span></p>
                        <p>Import Date: <span style="font-weight: bold; color: red"><%= product.getImportDate()%></span> (yyyy/mm/dd)</p>
                        <p>Using Date: <span style="font-weight: bold; color: red"><%= product.getUsingDate()%></span> (yyyy/mm/dd)</p>

                    </div>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                        <input type="hidden" name="productName" value="<%= product.getProductName()%>"/>
                        <input type="hidden" name="price" value="<%= product.getPrice()%>"/>
                        <input type="hidden" name="importDate" value="<%= product.getImportDate()%>"/>
                        <input type="hidden" name="usingDate" value="<%= product.getUsingDate()%>"/>
                        <input type="hidden" name="search" value="<%= search %>"/>
                        <%
                            if(product.getQuantity()>0){
                        %>
                                <input style="width: 20%;" type="submit" name="action" value="Add" />
                                <select name="quantity">
                        <% 
                                for(float i=0.5f; i<=3f && i<= product.getQuantity() ; i+=0.5f )
                                {
                        %>
                                <option value="<%= i %>"> <%= i %>kg</option>
                        <%
                                }
                            }else{
                        %>
                                <span style=" font-size: 20px; color: red; font-weight: bolder">Het Hang!!!</span>
                        <%
                            }
                        %>
                        </select>
                    </form>

                </div>
                <%
                    }
                %>

                <%
                        }
                    }
                %>		

            </div>


            <div class="row specialoffer">

                <div class="col-md-12">

                    <h2>Quality Product at Reasonable Prices.</h2>
                    <p>Surprisingly cheap and high quality</p>
                </div>

            </div>

            <div class="row">

                <div class="col-md-6">

                    <div >

                        <img  src="images/shopdisplay.jpg" />

                    </div>

                </div>

                <div class="col-md-3">

                    <div style="margin-top: 95px " class="sale">

                        <img src="images/cheaplogo.jpg" />



                    </div>

                </div>

                <div class="col-md-3">

                    <div style="margin-top: 15px " class="sale">

                        <img  src="images/highqualitymedal5.jpg" />



                    </div>

                </div>


            </div>



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
