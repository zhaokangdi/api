package com.bookshop.bookshop.entity;

/*
  构造返回前端的书信息
 */
public class BookInfo {

    private Book book;
    private String storeName;

    public Book getBook() {
        return book;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}
