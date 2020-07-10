package com.bookshop.bookshop.entity;

/*
   返回前端的订单信息
 */
public class OrderInfo {

    private Order order;
    private User user;
    private Store store;

    public Order getOrder() {
        return order;
    }

    public User getUser() {
        return user;
    }

    public Store getStore() {
        return store;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
