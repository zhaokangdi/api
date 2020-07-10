package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class ShoppingCart{

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "store_id")
    String storeId;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "number")
    String number;

    @Column(name = "price")
    String price;

    @Column(name = "total_price")
    String totalPrice;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getNumber() {
        return number;
    }

    public String getPrice() {
        return price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

}
