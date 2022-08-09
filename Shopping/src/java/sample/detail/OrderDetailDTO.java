/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.detail;

/**
 *
 * @author admin
 */
public class OrderDetailDTO {
    String detailID;
    float price;
    float quantity;
    String orderID;
    String productID;

    public OrderDetailDTO() {
        detailID="";
        price=0;
        quantity=0;
        orderID="";
        productID="";
    }

    public OrderDetailDTO(String detailID, float price, float quantity, String orderID, String productID) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.productID = productID;
    }

    public String getDetailID() {
        return detailID;
    }

    public float getPrice() {
        return price;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public String nextOrderDetailID(String orderDetailID){
        String nextOrderDetailID="";
        int nextNumber = Integer.parseInt(orderDetailID);
        nextOrderDetailID += (++nextNumber);        
        return nextOrderDetailID;
    }
    
}
