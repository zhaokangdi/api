package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartDAO extends JpaRepository<ShoppingCart, Integer> {

    List<ShoppingCart> findByUserId(String user_id);

}
