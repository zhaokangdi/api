package com.bookshop.bookshop.service;

import com.bookshop.bookshop.dao.BusinessDAO;
import com.bookshop.bookshop.dao.StoreDAO;
import com.bookshop.bookshop.entity.Business;
import com.bookshop.bookshop.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    BusinessDAO businessDAO;

    @Autowired
    StoreDAO storeDAO;

    //查看全部商店
    public List<Store> showAllStore(){
        return storeDAO.findAll();
    }

    //模糊查询店铺
    public List<Store> showLIKE(String condition){
        return storeDAO.findByPhoneContainsOrNameContainsOrAddressContainsOrIntroductionContains(condition,
                condition,condition,condition);
    }

    //管理员添加商店
    public Store addStore(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessOld.setState("已通过");

        Store store = new Store();
        store.setName(businessOld.getName());
        store.setPhone(businessOld.getPhone());
        store.setAddress(businessOld.getAddress());
        store.setIntroduction(businessOld.getIntroduction());

        Store storeNew  = storeDAO.save(store);
        businessOld.setId(storeNew.getId());
        businessDAO.save(businessOld);

        return storeNew;
    }

    //管理员拒绝商店
    public Business refuseBusiness(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessOld.setState("已拒绝");
        return businessDAO.save(businessOld);
    }

    //管理员删除商店
    public boolean deleteStore(Business business){

        Business businessOld = businessDAO.findByPhone(business.getPhone());
        businessDAO.delete(businessOld);

        Store storeOld = storeDAO.findByPhone(business.getPhone());
        storeDAO.delete(storeOld);

        return true;
    }

    //编辑店铺信息
    public boolean editStore(Store store){

        Store storeOld = storeDAO.findByPhone(store.getPhone());
        storeOld.setName(store.getName());
        storeOld.setAddress(store.getAddress());
        storeOld.setIntroduction(store.getIntroduction());

        //修改phone
        if(storeDAO.findByPhone(store.getPhone())==null){  //可以修改
            storeOld.setPhone(store.getPhone());
            storeDAO.save(store);
            return true;
        }
        else{//不可以修改
            return false;
        }
    }
}
