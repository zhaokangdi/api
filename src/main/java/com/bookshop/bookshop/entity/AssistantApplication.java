package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "assistant")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class AssistantApplication {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "store_id")
    String storeId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "state")
    String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getState() {
        return state;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setState(String state) {
        this.state = state;
    }

}
