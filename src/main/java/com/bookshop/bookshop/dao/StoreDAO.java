package com.bookshop.bookshop.dao;


import com.bookshop.bookshop.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreDAO extends JpaRepository<Store, Integer>{

    Store findByPhone(String phone);

    Store findById(int id);

    //模糊查询
    List<Store> findByPhoneContainsOrNameContainsOrAddressContainsOrIntroductionContains(String phoneCondition,
                                                                         String nameCondition,
                                                                         String addressCondition,
                                                                         String introductionCondition);

    void deleteById(String storeId);


}

