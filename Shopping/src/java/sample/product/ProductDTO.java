/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

/**
 *
 * @author admin
 */
public class ProductDTO {
    private String productID;
    private String productName;
    private String image;
    private float price;
    private float quantity;
    private String categoryID;
    private String importDate;
    private String usingDate;
    private byte status;
    
    public ProductDTO() {
        productID="";
        productName="";
        image="";
        price=0;
        quantity=0;
        categoryID="";
        importDate="";
        usingDate="";
        status=1;
    }

    public ProductDTO(String productID, String productName, String image, float price, float quantity, String categoryID, String importDate, String usingDate, byte status) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.importDate = importDate;
        this.usingDate = usingDate;
        this.status = status;
    }

    

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getImage() {
        return image;
    }

    public float getPrice() {
        return price;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getImportDate() {
        return importDate;
    }

    public String getUsingDate() {
        return usingDate;
    }
    
    public byte getStatus() {
        return status;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public void setUsingDate(String usingDate) {
        this.usingDate = usingDate;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
    
}
