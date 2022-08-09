/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author admin
 */
public class UserDAO {
    private static final String LOGIN="SELECT fullName,roleID,address,birthday,phone,email,status FROM tblUsers WHERE userID=? AND password=? AND status=1";
    public UserDTO checkLogin(String userID, String password) throws SQLException{
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        UserDTO loginUser = null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if(rs.next()){
                    String roleID = rs.getString("roleID");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String birtday = rs.getString("birthday");
                    byte status =  rs.getByte("status");
                    loginUser= new UserDTO(userID, fullName, "", roleID, address, birtday, phone, email, status);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();

            
        }
        return loginUser;
    }
}
