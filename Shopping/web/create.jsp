<%-- 
    Document   : create
    Created on : Mar 7, 2022, 10:02:09 PM
    Author     : admin
--%>

<%@page import="sample.product.ProductError"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
         <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser==null || !loginUser.getRoleID().equals("AD")){
                response.sendRedirect("login.jsp");
                return;
            }
            
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if(productError==null){
                productError = new ProductError();
            }
      
        %>
        <h1>Create Product:</h1>
        <form action="MainController" method="POST">
            Product ID: <input type="text" name="productID" required=""/>
            <%= productError.getProductIDError() %></br>
            Product Name: <input type="text" name="productName" required=""/>
            <%= productError.getProductNameError()%></br>
            Image: <input type="text" name="image" required=""/>
            <%= productError.getImageError()%></br>
            Price: <input type="number" name="price" min="1000" step="500" required=""/>
            <%= productError.getPriceError()%></br>
            Quantity: <input type="number" name="quantity" min="0.5" step="0.5" required=""/>
            <%= productError.getQuantityError()%></br>
            CategoryID: <input type="text" name="categoryID" required=""/>
            <%= productError.getCategoryIDError()%></br>
            ImportDate: <input type="date" name="importDate" required=""/>
            <%= productError.getImportDateError()%></br>
            UsingDate: <input type="date" name="usingDate" required=""/>
            <%= productError.getUsingDateError()%></br>
            <input type="submit" name="action" value="Create"/>
        </form>
            <a href="MainController?action=Search&search="> Go Back </a>
    </body>
</html>
