package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingDAO extends JpaRepository<Rating, Integer> {

    List<Rating> findByBookId(int book_id);

    List<Rating> findByUserId(String user_id);

}
