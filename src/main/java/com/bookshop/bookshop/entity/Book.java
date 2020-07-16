package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Book {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "store_id")
    int storeId;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Column(name = "publishing_house")
    String publishingHouse;

    @Column(name = "price")
    String price;

    @Column(name = "publishing_date")
    String publishingDate;

    @Column(name = "category")
    String category;

    @Column(name = "introduction")
    String introduction;

    @Column(name = "pic_path")
    String picPath;

    @Column(name = "score")
    String score;


    public int getId() {
        return id;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getPrice() {
        return price;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public String getCategory() {
        return category;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getPicPath() { return picPath; }

    public String getScore() { return score; }

    public void setId(int id) {
        this.id = id;
    }

    public void setStoreId(int store_id) {
        this.storeId = store_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPicPath(String picPath) { this.picPath = picPath; }

    public void setScore(String score) { this.score = score; }

    public void setId(Integer integer) {
    }
}
