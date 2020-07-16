package com.bookshop.bookshop.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "recommend")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Recommend {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "book_id")
    int bookId;

    @Column(name = "score")
    String score;

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }


    public String getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setScore(String score) {
        this.score = score;
    }


}
