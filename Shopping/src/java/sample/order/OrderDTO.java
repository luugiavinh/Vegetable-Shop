/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

/**
 *
 * @author admin
 */
public class OrderDTO {
    String orderID;
    String orderDate;
    float total;
    String userID;
    byte status;

    public OrderDTO() {
        orderID="";
        orderDate="";
        total=0;
        userID="";
        status=1;
    }
    
    public OrderDTO(String orderID, String orderDate, float total, String userID, byte status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public float getTotal() {
        return total;
    }

    public String getUserID() {
        return userID;
    }

    public byte getStatus() {
        return status;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
    
    public String nextOrderID(String orderID){
        String nextOrderID="";
        String [] tmp1 = orderID.split("O");
        int nextNumber = Integer.parseInt(tmp1[1]);
        nextOrderID += "O"+ (++nextNumber);        
        return nextOrderID;
    }
    
}
