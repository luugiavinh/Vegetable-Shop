/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.category.CategoryDAO;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.product.ProductError;

/**
 *
 * @author admin
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {
    private static final String ERROR= "SearchController";
    private static final String SUCCESS= "SearchController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        ProductError productError = new ProductError();
        try{
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String image = request.getParameter("image");
            float price = Float.parseFloat(request.getParameter("price"));
            float quantity = Float.parseFloat(request.getParameter("quantity"));
            String categoryID = request.getParameter("categoryID");
            String importDate = request.getParameter("importDate");
            String usingDate = request.getParameter("usingDate");
            CategoryDAO categoryDao = new CategoryDAO();
            List listCategoryID = categoryDao.getListAllCategoryID();
            boolean checkValidation=true;

            if(productName.length()<3 || productName.length()>20){
                productError.setProductNameError("Product Name must be in [3,20]");
                checkValidation= false;
            }
            
            if(!listCategoryID.contains(categoryID)){
                productError.setCategoryIDError("This Category doesnt exist in databases");
                checkValidation= false;
            }
            
            Calendar cal = Calendar.getInstance();
            
            String [] tmp1 = importDate.split("-");
            cal.set(Integer.parseInt(tmp1[0]), Integer.parseInt(tmp1[1])-1, Integer.parseInt(tmp1[2]));
            cal.add(Calendar.DAY_OF_MONTH, 10);
            Date dateImportCheck = new Date(cal.getTime().getTime());
            
            cal.set(Integer.parseInt(tmp1[0]), Integer.parseInt(tmp1[1])-1, Integer.parseInt(tmp1[2]));
            cal.add(Calendar.DAY_OF_MONTH, 7);
            Date minimumDateUsing = new Date(cal.getTime().getTime());
            
            Date currentTime = new Date(System.currentTimeMillis());
            String [] tmp2 = currentTime.toString().split("-");
            cal.set(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1])-1, Integer.parseInt(tmp2[2]));
            currentTime = new Date(cal.getTime().getTime());
  
            
            String [] tmp3 = usingDate.split("-");
            cal.set(Integer.parseInt(tmp3[0]), Integer.parseInt(tmp3[1])-1, Integer.parseInt(tmp3[2]));
            Date dateUsingCheck = new Date(cal.getTime().getTime());
            
            if(currentTime.compareTo(dateImportCheck) > 0){
                productError.setImportDateError("Han nhap khau khong duoc it hon ngay hien tai qua 10 ngay");
                checkValidation= false;
            }
            
            if(dateUsingCheck.compareTo(minimumDateUsing) < 0){
                productError.setUsingDateError("Han su dung phai lon hon hoac bang 7 ngay tinh tu ngay nhap khau");
                checkValidation= false;
            }
            
            if(checkValidation){
                ProductDTO product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, (byte)1);
                ProductDAO dao = new ProductDAO();
                boolean check= dao.updateProduct(product);
                if(check){
                    url=SUCCESS;
                }
            }
            else{
                request.setAttribute("PRODUCT_ERROR", productError);
            }

        }catch(Exception e){
            log("Error at UpdateController: "+e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
