/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author admin
 */
public class OrderDAO {
    private static final String SELECT_MAX_ORDER_ID = "SELECT MAX(orderID) as maxOrderID FROM tblOrder";
    private static final String CREATE= "INSERT INTO tblOrder(orderID, orderDate, total, userID, status) VALUES(?,?,?,?,?)";
    public String getMaxOrderID() throws SQLException{
        String maxOrderID="";
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SELECT_MAX_ORDER_ID);
                rs= ptm.executeQuery();
                if(rs.next()){
                    if(rs.getString("maxOrderID") == null){
                        maxOrderID="O0";
                    }
                    else{
                        maxOrderID= rs.getString("maxOrderID");
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
        return maxOrderID;
    }
    
    public boolean createOrder(OrderDTO order) throws SQLException{
        Connection conn= null;
        PreparedStatement ptm= null;
        boolean check= false;
        try{
            conn = DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(CREATE);
                ptm.setString(1, order.getOrderID());
                ptm.setString(2, order.getOrderDate());
                ptm.setFloat(3, order.getTotal());
                ptm.setString(4, order.getUserID());
                ptm.setByte(5, (byte) 1);
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
