package com.bookshop.bookshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Rating {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "book_id")
    int bookId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "score")
    String score;

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public String getScore() {
        return score;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
