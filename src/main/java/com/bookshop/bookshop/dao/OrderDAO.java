package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    Order findById(String userId);

    List<Order> findByUserId(String userId);

    List<Order> findByUserIdAndAndState(String userId,String state);

}
