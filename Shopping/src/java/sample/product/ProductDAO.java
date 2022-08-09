/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author admin
 */
public class ProductDAO {
    private static final String SEARCH_ALL="SELECT * FROM tblProduct WHERE status=1 AND usingDate>=?";
    private static final String SEARCH="SELECT * FROM tblProduct WHERE productName LIKE ? AND status=1 AND usingDate>=?";
    private static final String SEARCH_ALL_ADMIN="SELECT * FROM tblProduct WHERE status=1";
    private static final String SEARCH_ADMIN="SELECT * FROM tblProduct WHERE productName LIKE ? AND status=1";
    private static final String DELETE="UPDATE tblProduct SET status=0 WHERE productID=?";
    private static final String UPDATE="UPDATE tblProduct SET productName=?, image=?, price=?, quantity=?, categoryID=?, importDate=?, usingDate=? WHERE productID=?";
    private static final String SEARCH_CURRENT_QUANTITY="SELECT quantity FROM tblProduct WHERE productID=?";
    private static final String UPDATE_QUANTITY="UPDATE tblProduct SET quantity=? WHERE productID=?";
    private static final String CHECK_DUPLICATE =  "SELECT productName FROM tblProduct WHERE productID=?";
    private static final String CREATE= "INSERT INTO tblProduct(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status) VALUES(?,?,?,?,?,?,?,?,1)";
    public List<ProductDTO> getListProduct(String search) throws SQLException{
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        Date currentDate = new Date(System.currentTimeMillis());
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                if(!search.trim().isEmpty()){
                    ptm= conn.prepareStatement(SEARCH);
                    ptm.setString(1, "%"+search+"%");
                    ptm.setDate(2, currentDate);
                }
                else{
                    ptm= conn.prepareStatement(SEARCH_ALL);
                    ptm.setDate(1, currentDate);
                }
                
                rs = ptm.executeQuery();
                while(rs.next()){
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    float quantity = rs.getFloat("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    byte status = rs.getByte("status");
                    ProductDTO product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status);
                    listProduct.add(product);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();

            
        }
        return listProduct;
    }
    
    public List<ProductDTO> getListProductAdmin(String search) throws SQLException{
        List<ProductDTO> listProduct = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                if(!search.trim().isEmpty()){
                    ptm= conn.prepareStatement(SEARCH_ADMIN);
                    ptm.setString(1, "%"+search+"%");
                }
                else{
                    ptm= conn.prepareStatement(SEARCH_ALL_ADMIN);
                }

                rs = ptm.executeQuery();
                while(rs.next()){
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    float price = rs.getFloat("price");
                    float quantity = rs.getFloat("quantity");
                    String categoryID = rs.getString("categoryID");
                    String importDate = rs.getString("importDate");
                    String usingDate = rs.getString("usingDate");
                    byte status = rs.getByte("status");
                    ProductDTO product = new ProductDTO(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status);
                    listProduct.add(product);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();


        }
        return listProduct;
    }
    
    public boolean deleteProduct(String productID) throws SQLException{
        boolean check = false;
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
                check = ptm.executeUpdate()>0?true:false;
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
        return check;
    }
    
    public boolean updateProduct(ProductDTO product) throws SQLException{
        boolean check= false;
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getImage());
                ptm.setFloat(3, product.getPrice());
                ptm.setFloat(4, product.getQuantity());
                ptm.setString(5, product.getCategoryID());
                ptm.setString(6, product.getImportDate());
                ptm.setString(7, product.getUsingDate());
                ptm.setString(8, product.getProductID());
                check = ptm.executeUpdate()>0?true:false;
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
        return check;
    }
    
    public boolean checkQuantity(String productID, Float quantity) throws SQLException{
        boolean check= false;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SEARCH_CURRENT_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if(rs.next()){
                    Float currenQuantity = rs.getFloat("quantity");
                    if(currenQuantity>=quantity)
                        check=true;
                }
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
        return check;
    }
    
    public float getDatabaseQuantity(String productID) throws SQLException{
        float databaseQuantity=0;
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SEARCH_CURRENT_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if(rs.next()){
                   databaseQuantity = rs.getFloat("quantity");
                }
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
        return databaseQuantity;
    }
    
    public void updateQuantity(String productID, float cartQuantity, float databaseQuantity) throws SQLException{
        Connection conn=null;
        PreparedStatement ptm=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setFloat(1, databaseQuantity-cartQuantity);
                ptm.setString(2, productID);
                ptm.executeUpdate();
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
    }
    
    public boolean checkDuplicate(String productID) throws SQLException{
        boolean check= false;
        Connection conn= null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, productID);
                rs= ptm.executeQuery();
                if(rs.next()){
                    check= true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
            
        }
        return check;
    }
    
    public boolean create(ProductDTO product) throws SQLException{
        boolean check= false;
        Connection conn= null;
        PreparedStatement ptm= null;     
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CREATE);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getImage());
                ptm.setFloat(4, product.getPrice());
                ptm.setFloat(5, product.getQuantity());
                ptm.setString(6, product.getCategoryID());
                ptm.setString(7, product.getImportDate());
                ptm.setString(8, product.getUsingDate());
                check= ptm.executeUpdate()>0?true:false;
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
            
        }
        return check;
    }
}
