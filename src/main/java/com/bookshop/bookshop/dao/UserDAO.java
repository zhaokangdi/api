package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, Integer> {

    User findById(String id);    //通过id查询用户

    User findByIdAndPassword(String id,String password);

    void deleteById(String id);
}

