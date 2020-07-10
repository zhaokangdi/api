package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Order {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "book_id")
    String bookId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "number")
    String number;

    @Column(name = "total_price")
    String totalPrice;

    @Column(name = "place_date")
    String placeDate;

    @Column(name = "state")
    String state;

    public int getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public String getNumber() {
        return number;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getPlaceDate() {
        return placeDate;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPlaceDate(String placeDate) {
        this.placeDate = placeDate;
    }

    public void setState(String state) {
        this.state = state;
    }

}
