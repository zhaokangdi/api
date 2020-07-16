package com.bookshop.bookshop.entity;

/*
   返回客户端的购物车信息
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class ShoppingCartInfo {

    private ShoppingCart shoppingCart;
    private String picPath;
    private String storeName;

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
