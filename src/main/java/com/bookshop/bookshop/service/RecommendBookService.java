package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BookDAO;
import com.bookshop.bookshop.dao.RecommendBookDAO;
import com.bookshop.bookshop.entity.Book;
import com.bookshop.bookshop.entity.Recommend;
import com.bookshop.bookshop.entity.RecommendBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RecommendBookService {

    @Autowired
    RecommendBookDAO recommendBookDAO;

    @Autowired
    BookDAO bookDAO;

    public ArrayList<RecommendBook> showAllRecommend(String userId){

        List<Recommend> recommends = recommendBookDAO.findByUserIdOrderByScoreDesc(userId);
        ArrayList<RecommendBook> recommendBooks = new ArrayList<>();

        for(Recommend recommend: recommends){
            int bookId = recommend.getBookId();
            Book book = bookDAO.findById(bookId);

            RecommendBook recommendBook = new RecommendBook();
            recommendBook.setBookId(bookId);
            recommendBook.setName(book.getName());

            recommendBooks.add(recommendBook);
        }
        return recommendBooks;
    }

}
