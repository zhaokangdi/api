package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.AssistantApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationDAO extends JpaRepository<AssistantApplication,Integer>{

    AssistantApplication findByStoreIdAndUserId(int store_id,String user_id);

    List<AssistantApplication> findByStoreIdAndState(int store_id, String state);

    List<AssistantApplication> findByStoreId(int store_id);

    AssistantApplication findByUserId(String user_id);

    AssistantApplication findById(int id);
}
