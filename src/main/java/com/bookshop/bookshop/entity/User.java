package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class User {

    @Id()
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    String id;  //用户手机号

    @Column(name = "username")
    String username;  //用户名

    @Column(name = "password")
    String password;  //用户密码

    @Column(name = "address")
    String address;   //用户住址

    @Column(name = "role")
    String role;      //用户身份：普通用户、店铺助理

    @Column(name = "salt")
    String salt;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

}