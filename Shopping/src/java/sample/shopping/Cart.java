/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;
import sample.product.ProductDTO;

/**
 *
 * @author admin
 */
public class Cart {
    Map<String,ProductDTO> cart;
    

    public Cart() {
        cart= new HashMap<>();
    }
    

    public Cart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }
    

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public boolean add(ProductDTO vegetable){
        boolean check;
        if(this.cart==null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(vegetable.getProductID())){
            float currentQuantity = this.cart.get(vegetable.getProductID()).getQuantity();
            vegetable.setQuantity(currentQuantity+vegetable.getQuantity());
        }
        this.cart.put(vegetable.getProductID(), vegetable);
        check=true;
        return check;
    }
    
    public boolean remove(String productID){
        boolean check= false;
        if(this.cart!=null){
            if(this.cart.containsKey(productID)){
                this.cart.remove(productID);
                check=true;
            }
        }
        return check;
        
    }
    
    public boolean update(String productID, ProductDTO product){
        boolean check = false;
        if(this.cart!=null){
            if(this.cart.containsKey(productID)){
                this.cart.replace(productID, product);
                check=true;
            }
        }
        return check;
    }
    
    
}
