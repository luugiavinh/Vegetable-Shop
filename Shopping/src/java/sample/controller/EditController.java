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
@WebServlet(name = "EditController", urlPatterns = {"/EditController"})
public class EditController extends HttpServlet {
    private static final String ERROR="viewCart.jsp";
    private static final String SUCCESS="viewCart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        try{
            String productID = request.getParameter("productID");
            float newQuantity= Float.parseFloat(request.getParameter("quantity"));
            HttpSession session = request.getSession();
            Cart cart= (Cart) session.getAttribute("CART");
            ProductDAO dao = new ProductDAO();
            ProductDTO product;
            if(cart!=null){
                if(cart.getCart().containsKey(productID)){
                    if(dao.checkQuantity(productID, newQuantity)){                        
                        product= cart.getCart().get(productID);
                        product.setQuantity(newQuantity);
                        if(cart.update(productID, product));
                        {   
                            session.setAttribute("CART", cart);
                            url=SUCCESS;
                        }
                    }
                    else{
                        request.setAttribute("MESSAGE", "So luong san pham khong du, khong the edit");
                    }

                }
            }
        }catch(Exception e){
            log("Error at EditController: " + e.toString());
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
