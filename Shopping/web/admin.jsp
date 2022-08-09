<%-- 
    Document   : admin
    Created on : Feb 28, 2022, 2:06:48 PM
    Author     : admin
--%>

<%@page import="sample.product.ProductError"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if(loginUser==null || !loginUser.getRoleID().equals("AD")){
                response.sendRedirect("login.jsp");
                return;
            }
            
            String fullName= loginUser.getFullName();
            
            String search = request.getParameter("search");
            if(search==null){
                search="";
            }
            
            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if(productError==null){
                productError = new ProductError();
            }    
      
        %>
        <h1>WELCOME ADMIN:</h1>
        <h2><%= fullName %></h2>
        <form action="MainController">
            <input type="submit" name="action" value="Logout" />
        </form>
        
         <form action="MainController">
            Search <input type="text" name="search" value="<%= search %>" required=""/>
           <input type="submit" name="action" value="Search"/>
        </form>
        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if(listProduct!=null){
                if(listProduct.size()>0){
        %>
        <table border="1" >
            
            <thead>
                <tr>
                    <th>Num</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>CategoryID</th>
                    <th>ImportDate</th>
                    <th>UsingDate</th>
                    <th>Delete</th>
                    <th>Update</th>

                </tr>
            </thead>
            <tbody>
                <%
                    int countProduct=0;
                    for ( ProductDTO product : listProduct){
                %>
                <tr>
                    <form action="MainController" method="POST">
            
                    <td><%= countProduct++ %></td>
                    <td><input type="text" name="productID" value="<%= product.getProductID() %>" readonly=""/></td>
                    <td><input type="text" name="productName" value="<%= product.getProductName() %>" required=""/></td>
                    <td><input type="text" name="image" value="<%= product.getImage() %>" required="" /></td>
                    <td><input style="width: 90%" type="number" name="price" value="<%= product.getPrice() %>" min="1000" step="500" required=""/></td>
                    <td><input type="number" name="quantity" value="<%= product.getQuantity() %>"  min="0.5" step="0.5" required="" /></td>
                    <td><input type="text" name="categoryID" value="<%= product.getCategoryID() %>" required="" /></td>
                    <td><input type="date" name="importDate" value="<%= product.getImportDate() %>" required="" /></td>
                    <td><input type="date" name="usingDate" value="<%= product.getUsingDate() %>" required="" /></td>
                    <td><a href="MainController?search=<%= search %>&action=Delete&productID=<%= product.getProductID()%>">Delete</a></td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="search" value="<%= search %>"/>
                    </td>
                    </form>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>           
         
        <%
            }
                }
        %>
        <a href="create.jsp">Create Product</a></br>
        <%= productError.getProductNameError()%></br>
        <%= productError.getCategoryIDError()%></br>
        <%= productError.getImportDateError()%></br>
        <%= productError.getUsingDateError()%></br>
</html>
