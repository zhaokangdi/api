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
    int storeId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "username")
    String username;

    @Column(name = "useraddress")
    String useraddress;

    @Column(name = "state")
    String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public String getState() {
        return state;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

}
