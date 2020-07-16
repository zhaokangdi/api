package com.bookshop.bookshop.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class RecommendBook {

    int bookId;
    String name;

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setName(String name) {
        this.name = name;
    }

}
