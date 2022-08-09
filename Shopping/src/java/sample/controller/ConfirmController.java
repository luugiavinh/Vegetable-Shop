/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.shopping.Cart;

/**
 *
 * @author admin
 */
@WebServlet(name = "ConfirmController", urlPatterns = {"/ConfirmController"})
public class ConfirmController extends HttpServlet {
    private static final String ERROR= "confirm.jsp";
    private static final String SUCCESS= "CreateOrderController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try{
            String report = "";
            boolean errorCheck=false;
            HttpSession session= request.getSession(false);
            if(session!=null){
                Cart cart = (Cart) session.getAttribute("CART");
                if(cart!=null){
                    ProductDAO dao = new ProductDAO();
                    for( ProductDTO product : cart.getCart().values()){
                        if(!dao.checkQuantity(product.getProductID(), product.getQuantity())){
                            report += product.getProductName() + " -- ";
                            errorCheck= true;
                        }
                    }
                    request.setAttribute("MESSAGE", "So luong -- " + report +" khong con du trong kho");
                    if(!errorCheck){  
                        float databaseQuantity;
                        for( ProductDTO product : cart.getCart().values()){
                            databaseQuantity = dao.getDatabaseQuantity(product.getProductID());
                            dao.updateQuantity(product.getProductID(), product.getQuantity(), databaseQuantity);
                        }
                        request.setAttribute("MESSAGE", "Cam on vi da mua hang cua chung toi !!!");
                        url=SUCCESS;
                    }
 
                }
                
            }          
        }catch(Exception e){
            log("Error at ConfirmController: "+ e.toString());
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
