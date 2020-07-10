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
    String bookId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "score")
    String score;

    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public String getScore() {
        return score;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
