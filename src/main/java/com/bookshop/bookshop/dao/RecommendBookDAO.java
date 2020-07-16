package com.bookshop.bookshop.dao;


import com.bookshop.bookshop.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendBookDAO  extends JpaRepository<Recommend, Integer> {

    List<Recommend> findByUserIdOrderByScoreDesc(String userId);

}
