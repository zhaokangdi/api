package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class LoginEntity {

    private String userId; //联系方式
    private String name;
    private String role;
    private String address;

    public String getId() {
        return userId;
    }

    public String getRole() { return role; }

    public String getName(){ return name; }

    public String getAddress() { return address; }

    public void setId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
