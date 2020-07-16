package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    //店铺中的全部图书
    List<Book> findByStoreId(int storeId);

    //按评分降序
    List<Book> findByStoreIdOrderByStoreIdDesc(int storeId);

    //某一类别的全部图书
    List<Book> findByCategory(String category);

    //某作者的全部图书
    List<Book> findByAuthor(String author);

    //某书名
    List<Book> findByName(String name);

    //模糊查询
    List<Book> findByNameContainsOrAuthorContainsOrPublishingHouseContainsOrIntroductionContains(
            String nameCondition,
            String authorCondition,
            String publishingHouseCondition,
            String introductionCondition);

    Book findById(int id);

    void deleteById(int id);

    List<Book> findByCategoryOrderByScoreDesc(String category);


}
