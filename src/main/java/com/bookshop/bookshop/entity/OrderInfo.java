package com.bookshop.bookshop.entity;

/*
   返回前端的订单信息
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class OrderInfo {

    private Order order;
    private User user;
    private Store store;
    private Book book;

    public Order getOrder() {
        return order;
    }

    public User getUser() { return user; }

    public Store getStore() {
        return store;
    }

    public Book getBook() { return book; }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setBook(Book book) { this.book = book; }
}
