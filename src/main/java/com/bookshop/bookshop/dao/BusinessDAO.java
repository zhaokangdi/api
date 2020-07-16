package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessDAO extends JpaRepository<Business,Integer> {

    Business findByPhone(String phone);

    Business findByPhoneAndPassword(String phone,String password);

    List<Business> findByState(String state);

    void deleteByPhone(String phone);

    Business findByStoreId(int store_id);
}
