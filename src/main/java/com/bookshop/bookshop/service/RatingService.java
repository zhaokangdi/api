package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.RatingDAO;
import com.bookshop.bookshop.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingDAO ratingDAO;

    //查找某书的全部评分
    public List<Rating> checkRating(String book_id){
        return ratingDAO.findByBookId(book_id);
    }

    //计算分数的平均
    public double calAverageScore(List<Rating> ratings){

        int totalScore = 0;

        for(Rating rating: ratings){
            int rate = Integer.parseInt(rating.getScore());
            totalScore += rate;
        }

        return totalScore/(ratings.size());
    }

    //显示用户的全部评价
    public List<Rating> showAllRatingUser(String user_id){
        return ratingDAO.findByUserId(user_id);
    }
}
