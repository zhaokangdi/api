package com.bookshop.bookshop.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class BookMain {

    List<Book> bookList = new ArrayList<Book>();
    ArrayList<RecommendBook> recommendBooks = new ArrayList<RecommendBook>();

    public List<Book> getBookList() {
        return bookList;
    }

    public ArrayList<RecommendBook> getRecommendBooks() {
        return recommendBooks;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void setRecommendBooks(ArrayList<RecommendBook> recommendBooks) {
        this.recommendBooks = recommendBooks;
    }
}
