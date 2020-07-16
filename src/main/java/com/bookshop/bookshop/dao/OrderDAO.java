package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    Order findById(int Id);

    List<Order> findByUserId(String userId);

    List<Order> findByBookId(int bookId);

    List<Order> findByUserIdAndState(String userId,String state);

    List<Order> findByBookIdAndState(int bookId,String state);


}
