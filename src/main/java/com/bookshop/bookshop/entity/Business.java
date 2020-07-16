package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "business")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Business {

    @Id()
    @Column(name = "phone")
    String phone;

    @Column(name = "store_id")
    int storeId;

    @Column(name = "name")
    String name;

    @Column(name = "password")
    String password;

    @Column(name = "address")
    String address;

    @Column(name = "store_name")
    String storeName;

    @Column(name = "state")
    String state;

    @Column(name = "introduction")
    String introduction;

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getStoreName() { return storeName; }

    public String getState() {
        return state;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
