/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.category;

import java.sql.Connection;
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
public class CategoryDAO {
    private static final String SEARCH_ALL = "SELECT categoryID FROM tblCategory";
    
    public List getListAllCategoryID() throws SQLException{
        List listCategoryID = new ArrayList();
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SEARCH_ALL);
                rs = ptm.executeQuery();
                while(rs.next()){         
                    listCategoryID.add(rs.getString("categoryID"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();

            
        }
        return listCategoryID;        
    }
}
