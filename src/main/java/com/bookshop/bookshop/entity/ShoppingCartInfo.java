package com.bookshop.bookshop.entity;

/*
   返回客户端的购物车信息
 */
public class ShoppingCartInfo {

    private ShoppingCart shoppingCart;
    private String store_name;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

}
