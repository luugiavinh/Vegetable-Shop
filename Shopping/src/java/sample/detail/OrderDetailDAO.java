/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.detail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author admin
 */
public class OrderDetailDAO {
    private static final String SELECT_MAX_ORDER__DETAIL_ID = "SELECT MAX(detailID) AS maxOrderDetailID FROM tblOrderDetail";
    private static final String CREATE= "INSERT INTO tblOrderDetail(detailID, price, quantity, orderID, productID) VALUES(?,?,?,?,?)";
    public String getMaxOrderDetailID() throws SQLException{
        String maxOrderDetailID="";
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SELECT_MAX_ORDER__DETAIL_ID);
                rs= ptm.executeQuery();
                if(rs.next()){
                    if(rs.getString("maxOrderDetailID") == null){
                        maxOrderDetailID="0";
                    }
                    else{
                        maxOrderDetailID= rs.getString("maxOrderDetailID");
                    }
                }            
            }           
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();           
        }
        return maxOrderDetailID;
    }
    
    public boolean createOrderDetail(OrderDetailDTO orderDetail) throws SQLException{
        Connection conn= null;
        PreparedStatement ptm= null;
        boolean check= false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CREATE);
                ptm.setString(1, orderDetail.getDetailID());
                ptm.setFloat(2, orderDetail.getPrice());
                ptm.setFloat(3, orderDetail.getQuantity());
                ptm.setString(4, orderDetail.getOrderID());
                ptm.setString(5, orderDetail.getProductID());
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
