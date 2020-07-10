package com.bookshop.bookshop.entity;

/*
  构造返回前端的书信息
 */
public class BookInfo {

    private Book book;
    private String store;

    public Book getBook() {
        return book;
    }

    public String getStore() {
        return store;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setStore(String store) {
        this.store = store;
    }

}
