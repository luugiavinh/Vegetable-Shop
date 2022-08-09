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
public class ProductError {
    private String productIDError;
    private String productNameError;
    private String imageError;
    private String priceError;
    private String quantityError;
    private String categoryIDError;
    private String importDateError;
    private String usingDateError;

    public ProductError() {
        this.productIDError="";
        this.productNameError="";
        this.imageError="";
        this.priceError="";
        this.quantityError="";
        this.categoryIDError="";
        this.importDateError="";
        this.usingDateError="";
    }

    public ProductError(String productIDError, String productNameError, String imageError, String priceError, String quantityError, String categoryIDError, String importDateError, String usingDateError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.imageError = imageError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.categoryIDError = categoryIDError;
        this.importDateError = importDateError;
        this.usingDateError = usingDateError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public String getImageError() {
        return imageError;
    }

    public String getPriceError() {
        return priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public String getUsingDateError() {
        return usingDateError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public void setUsingDateError(String usingDateError) {
        this.usingDateError = usingDateError;
    }
    
    
}
